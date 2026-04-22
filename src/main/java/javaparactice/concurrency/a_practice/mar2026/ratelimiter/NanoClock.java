package javaparactice.concurrency.a_practice.mar2026.ratelimiter;

/**
 * Abstraction over {@link System#nanoTime()}.
 *
 * Injecting time makes the algorithm deterministic in tests and avoids
 * coupling correctness to wall-clock sleeps.
 */
@FunctionalInterface
public interface NanoClock {
    long nanoTime();
}
