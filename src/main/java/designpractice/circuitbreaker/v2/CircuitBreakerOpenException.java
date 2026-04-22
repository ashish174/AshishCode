package designpractice.circuitbreaker.v2;

/**
 * Exception thrown when a call is rejected by a CircuitBreaker
 * that is in OPEN (or constrained HALF_OPEN) state.
 *
 * <p>Typically this is treated as a fast-fail signal indicating that the
 * downstream dependency is unhealthy or unavailable, and the caller
 * should avoid retrying immediately.
 */
public class CircuitBreakerOpenException extends RuntimeException {
    public CircuitBreakerOpenException(String message) {
        super(message);
    }
}