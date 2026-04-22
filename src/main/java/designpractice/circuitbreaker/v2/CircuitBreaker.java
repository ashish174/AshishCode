package designpractice.circuitbreaker.v2;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A simple, thread-safe Circuit Breaker implementation.
 *
 * <p>States:
 * <ul>
 *   <li><b>CLOSED</b>:
 *     <ul>
 *       <li>All calls are allowed through.</li>
 *       <li>Failures are counted; when the number of consecutive failures
 *           reaches {@code failureThreshold}, the breaker goes to OPEN.</li>
 *           Other logic also can be used like
 *              “Time‑windowed failure counter: Open the breaker if there are more than 50 failures in 1 minute,” or
 *              “Rate or ratio based breaker: Open the breaker if error rate > 20% over the last 10 minutes,”
 *     </ul>
 *   </li>
 *   <li><b>OPEN</b>:
 *     <ul>
 *       <li>All calls fail fast with {@link CircuitBreakerOpenException}.</li>
 *       <li>After {@code openTimeout} elapses, the breaker transitions to HALF_OPEN.</li>
 *     </ul>
 *   </li>
 *   <li><b>HALF_OPEN</b>:
 *     <ul>
 *       <li>Allows a limited number of "trial" calls.</li>
 *       <li>If a trial call succeeds, the breaker transitions back to CLOSED.</li>
 *       <li>If any trial call fails, the breaker transitions back to OPEN.</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <p>Typical usage:
 * <pre>{@code
 * CircuitBreaker breaker = CircuitBreaker.newBuilder()
 *     .failureThreshold(5)
 *     .openTimeout(Duration.ofSeconds(10))
 *     .halfOpenMaxCalls(1)
 *     .build();
 *
 * String result = breaker.call(() -> remoteClient.getData());
 * }</pre>
 *
 * <p>This is intended as an interview‑ready, compact design:
 * <ul>
 *   <li>Single object encapsulates state and policy.</li>
 *   <li>Thread safety via minimal synchronization around state transitions.</li>
 *   <li>Clock is injectable for testability.</li>
 * </ul>
 */
public final class CircuitBreaker {

    /**
     * High-level lifecycle state of the breaker.
     */
    public enum State {
        CLOSED,
        OPEN,
        HALF_OPEN
    }

    /** Number of consecutive failures that will trip the breaker from CLOSED -> OPEN. */
    private final int failureThreshold;

    /** How long to stay OPEN before allowing HALF_OPEN trial calls. */
    private final Duration openTimeout;

    /**
     * How many trial calls are allowed while in HALF_OPEN.
     * Typically 1 is enough for many designs.
     */
    private final int halfOpenMaxCalls;

    /** Clock injected for easier testing (e.g. fixed clock, mocked time). */
    private final Clock clock;

    /** Current state of the breaker (CLOSED / OPEN / HALF_OPEN). */
    private volatile State state;

    /** When the breaker last entered its current state. */
    private volatile Instant stateSince;

    /**
     * Number of consecutive failures observed while in CLOSED or HALF_OPEN.
     * Used for deciding when to trip the breaker.
     */
    private final AtomicInteger failureCount = new AtomicInteger(0);

    /**
     * In HALF_OPEN, tracks how many trial calls have been attempted.
     * Limits exposure to potentially unhealthy downstream.
     */
    private final AtomicInteger halfOpenTrialCount = new AtomicInteger(0);

    private CircuitBreaker(int failureThreshold,
                           Duration openTimeout,
                           int halfOpenMaxCalls,
                           Clock clock) {

        if (failureThreshold <= 0) {
            throw new IllegalArgumentException("failureThreshold must be > 0");
        }
        if (halfOpenMaxCalls <= 0) {
            throw new IllegalArgumentException("halfOpenMaxCalls must be > 0");
        }
        this.failureThreshold = failureThreshold;
        this.openTimeout = Objects.requireNonNull(openTimeout, "openTimeout must not be null");
        this.halfOpenMaxCalls = halfOpenMaxCalls;
        this.clock = Objects.requireNonNull(clock, "clock must not be null");

        // Initial state: CLOSED, healthy by default.
        this.state = State.CLOSED;
        this.stateSince = clock.instant();
    }

    /**
     * Executes the given operation according to circuit breaker rules.
     *
     * <p>Behavior:
     * <ul>
     *   <li>If breaker is OPEN and timeout has not elapsed → throws {@link CircuitBreakerOpenException}.</li>
     *   <li>If breaker is OPEN and timeout has elapsed → moves to HALF_OPEN, then tries a trial call.</li>
     *   <li>If breaker is HALF_OPEN:
     *       <ul>
     *         <li>On success → moves back to CLOSED.</li>
     *         <li>On failure → moves back to OPEN.</li>
     *       </ul>
     *   </li>
     *   <li>If breaker is CLOSED:
     *       <ul>
     *         <li>On success → failure count is reset.</li>
     *         <li>On failure → failure count increments; when threshold reached → move to OPEN.</li>
     *       </ul>
     *   </li>
     * </ul>
     *
     * @param operation the work to be executed if the breaker allows it
     * @param <T>       result type of the operation
     * @return the result of the operation
     * @throws CircuitBreakerOpenException if the breaker rejects the call in OPEN/HALF_OPEN state
     * @throws Exception                   if the underlying operation throws
     */
    public <T> T call(Callable<T> operation) throws Exception {
        Objects.requireNonNull(operation, "operation must not be null");

        // First, see if we need to transition OPEN -> HALF_OPEN due to timeout.
        State currentState = getStateAndMaybeTransition();

        switch (currentState) {
            case OPEN:
                // Still in OPEN and not yet timed out: fail fast.
                throw new CircuitBreakerOpenException("Circuit breaker is OPEN");
            case HALF_OPEN:
                // Allow a limited number of trial calls.
                return callHalfOpen(operation);
            case CLOSED:
            default:
                // Normal happy path: CLOSED.
                return callClosed(operation);
        }
    }

    /**
     * Returns the current state, and performs time-based transition
     * from OPEN to HALF_OPEN if the openTimeout has elapsed.
     *
     * @return the current state of the breaker after any time-based transition
     */
    public State getStateAndMaybeTransition() {
        State current = this.state;

        if (current == State.OPEN) {
            Instant now = clock.instant();
            // If we've been OPEN longer than openTimeout, consider HALF_OPEN.
            if (stateSince.plus(openTimeout).isBefore(now)) {
                synchronized (this) {
                    // Double-check within the lock in case another thread
                    // already transitioned while we were reading.
                    if (this.state == State.OPEN &&
                            this.stateSince.plus(openTimeout).isBefore(clock.instant())) {
                        transitionToHalfOpen();
                    }
                }
            }
        }
        return this.state;
    }

    /**
     * Returns the current state without changing it.
     */
    public State getState() {
        return this.state;
    }

    // -------- Internal call routing --------

    /**
     * Logic when the breaker is CLOSED.
     * All calls are allowed; failures increment failureCount.
     */
    private <T> T callClosed(Callable<T> operation) throws Exception {
        try {
            T result = operation.call();
            // Success: reset failure counter because the downstream appears healthy.
            failureCount.set(0);
            return result;
        } catch (Exception e) {
            // Increment consecutive failure count.
            int failures = failureCount.incrementAndGet();

            // If the threshold is crossed, attempt to trip to OPEN.
            if (failures >= failureThreshold) {
                synchronized (this) {
                    // Double-check inside lock to avoid race with another thread
                    // that might have already opened the breaker.
                    if (state == State.CLOSED && failureCount.get() >= failureThreshold) {
                        transitionToOpen();
                    }
                }
            }
            throw e;
        }
    }

    /**
     * Logic when the breaker is HALF_OPEN.
     * Only a limited number of trial calls are permitted.
     */
    private <T> T callHalfOpen(Callable<T> operation) throws Exception {
        // Atomically track how many trial calls have started in HALF_OPEN.
        int trialNumber = halfOpenTrialCount.incrementAndGet();
        if (trialNumber > halfOpenMaxCalls) {
            // Too many trial calls: behave as though still OPEN.
            throw new CircuitBreakerOpenException("Circuit breaker HALF_OPEN max trial calls exceeded");
        }

        try {
            T result = operation.call();
            // Trial succeeded: move to CLOSED and reset counters.
            synchronized (this) {
                transitionToClosed();
            }
            return result;
        } catch (Exception e) {
            // Trial failed: move back to OPEN and restart timeout.
            synchronized (this) {
                transitionToOpen();
            }
            throw e;
        }
    }

    // -------- State transition helpers --------

    /**
     * Move the breaker to OPEN:
     *  - future calls will be rejected until timeout elapses.
     */
    private void transitionToOpen() {
        state = State.OPEN;
        stateSince = clock.instant();
        // In OPEN, trial count is irrelevant; reset for clarity.
        halfOpenTrialCount.set(0);
        // failureCount is left as-is (can be useful for diagnostics).
    }

    /**
     * Move the breaker to HALF_OPEN:
     *  - we will allow limited trial calls to test downstream health.
     */
    private void transitionToHalfOpen() {
        state = State.HALF_OPEN;
        stateSince = clock.instant();
        // Reset counts before starting trial phase.
        failureCount.set(0);
        halfOpenTrialCount.set(0);
    }

    /**
     * Move the breaker to CLOSED:
     *  - normal operation resumes, counters reset.
     */
    private void transitionToClosed() {
        state = State.CLOSED;
        stateSince = clock.instant();
        failureCount.set(0);
        halfOpenTrialCount.set(0);
    }

    // -------- Builder for convenient configuration --------

    /**
     * Creates a new builder with sensible defaults.
     *
     * Defaults:
     *  - failureThreshold = 5
     *  - openTimeout = 30 seconds
     *  - halfOpenMaxCalls = 1
     *  - clock = system UTC clock
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Fluent builder pattern to configure and construct a CircuitBreaker.
     */
    public static final class Builder {
        private int failureThreshold = 5;
        private Duration openTimeout = Duration.ofSeconds(30);
        private int halfOpenMaxCalls = 1;
        private Clock clock = Clock.systemUTC();

        /**
         * How many consecutive failures will trip the breaker from CLOSED to OPEN.
         */
        public Builder failureThreshold(int failureThreshold) {
            this.failureThreshold = failureThreshold;
            return this;
        }

        /**
         * How long to remain OPEN before transitioning to HALF_OPEN and allowing trial calls.
         */
        public Builder openTimeout(Duration openTimeout) {
            this.openTimeout = openTimeout;
            return this;
        }

        /**
         * Maximum number of trial calls allowed while in HALF_OPEN.
         */
        public Builder halfOpenMaxCalls(int halfOpenMaxCalls) {
            this.halfOpenMaxCalls = halfOpenMaxCalls;
            return this;
        }

        /**
         * Custom clock (useful for testing or simulating time).
         */
        public Builder clock(Clock clock) {
            this.clock = clock;
            return this;
        }

        /**
         * Builds the configured CircuitBreaker instance.
         */
        public CircuitBreaker build() {
            return new CircuitBreaker(failureThreshold, openTimeout, halfOpenMaxCalls, clock);
        }
    }
}
