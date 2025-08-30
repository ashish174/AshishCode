package designpractice.circuitbreaker;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Custom implementation of a Circuit Breaker pattern.
 * It prevents a system from making calls to a failing service
 * and allows recovery after a reset timeout.
 */
public class CustomCircuitBreaker {
    /** Possible states of the circuit breaker */
    private enum State { CLOSED, OPEN, HALF_OPEN }

    private State state = State.CLOSED;
    private final int failureThreshold;
    private final int successThreshold;
    private final Duration resetTimeout;
    private AtomicInteger failureCount = new AtomicInteger(0);
    private AtomicInteger successCount = new AtomicInteger(0);
    private long lastFailureTime = 0;

    /**
     * Constructor to initialize the Circuit Breaker with given thresholds and timeout.
     *
     * @param failureThreshold Number of failures before the circuit opens
     * @param successThreshold Number of successes needed to close the circuit from half-open state
     * @param resetTimeout Time duration before transitioning to half-open state from open
     */
    public CustomCircuitBreaker(int failureThreshold, int successThreshold, Duration resetTimeout) {
        this.failureThreshold = failureThreshold;
        this.successThreshold = successThreshold;
        this.resetTimeout = resetTimeout;
    }

    /**
     * Determines if a request should be allowed based on the circuit state.
     *
     * @return true if the request is allowed, false otherwise
     */
    public synchronized boolean allowRequest() {
        if (state == State.OPEN) {
            if (System.currentTimeMillis() - lastFailureTime > resetTimeout.toMillis()) {
                state = State.HALF_OPEN;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Records a successful request and updates the circuit state accordingly.
     */
    public synchronized void recordSuccess() {
        if (state == State.HALF_OPEN) {
            successCount.incrementAndGet();
            if (successCount.get() >= successThreshold) {
                reset();
            }
        } else {
            reset();
        }
    }

    /**
     * Records a failed request and updates the circuit state accordingly.
     */
    public synchronized void recordFailure() {
        failureCount.incrementAndGet();
        if (failureCount.get() >= failureThreshold) {
            trip();
        }
    }

    /**
     * Resets the circuit breaker back to the closed state.
     */
    private void reset() {
        state = State.CLOSED;
        failureCount.set(0);
        successCount.set(0);
    }

    /**
     * Trips the circuit breaker into the open state to prevent further requests.
     */
    private void trip() {
        state = State.OPEN;
        lastFailureTime = System.currentTimeMillis();
    }

    /**
     * Main method to demonstrate the circuit breaker in action.
     * Simulates service calls and transitions between states.
     */
    public static void main(String[] args) {
        CustomCircuitBreaker circuitBreaker = new CustomCircuitBreaker(3, 2, Duration.ofSeconds(5));
        for (int i = 0; i < 10; i++) {
            if (circuitBreaker.allowRequest()) {
                try {
                    if (Math.random() > 0.7) {
                        System.out.println("Service call success");
                        circuitBreaker.recordSuccess();
                    } else {
                        throw new RuntimeException("Service failure");
                    }
                } catch (Exception e) {
                    System.out.println("Service failed");
                    circuitBreaker.recordFailure();
                }
            } else {
                System.out.println("Circuit Breaker is OPEN - Skipping request");
            }
            try { Thread.sleep(1000); } catch (InterruptedException e) { }
        }
    }
}
