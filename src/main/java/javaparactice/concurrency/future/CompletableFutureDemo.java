package javaparactice.concurrency.future;

import java.util.concurrent.CompletableFuture;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * Demonstrates the most useful CompletableFuture operations in a single,
 * easy‑to‑run program.
 *
 * <p>Features shown:
 *
 * <ul>
 *   <li>Creating async tasks (supplyAsync / runAsync)</li>
 *   <li>Chaining with thenApply / thenAccept / thenCompose / thenCombine</li>
 *   <li>Combining many futures with allOf / anyOf</li>
 *   <li>Exception handling: exceptionally, handle, whenComplete</li>
 *   <li>Timeouts (completeOnTimeout) and explicit cancellation</li>
 *   <li>Using a custom Executor for better thread‑pool control</li>
 *   <li>Recording results in a POJO (record) – makes the demo concise</li>
 * </ul>
 *
 * Run {@code java demo.cf.CompletableFutureDemo} to see the output.
 *
 * @author Oracle‑Code‑Assist
 */
public final class CompletableFutureDemo {
/*
    *//* --------------------------------------------------------------------- *
     *  Logger & Executor                                                    *
     * --------------------------------------------------------------------- *//*
    private static final Logger LOG = Logger.getLogger(CompletableFutureDemo_old.class.getName());

    *//** A fixed‑size pool that also supports scheduling (delay, periodic). *//*
    private static final ScheduledExecutorService POOL =
            Executors.newScheduledThreadPool(
                    Runtime.getRuntime().availableProcessors(),
                    r -> {
                        Thread t = new Thread(r);
                        t.setName("cf-worker-" + t.getId());
                        t.setDaemon(true);
                        return t;
                    });

    *//* --------------------------------------------------------------------- *
     *  Small data carrier – just to make the demo output tidy               *
     * --------------------------------------------------------------------- *//*
    *//** Simple immutable DTO that represents the result of an async step. *//*
    private record Result(String description, long durationMs) {
        @Override public String toString() {
            return description + " [" + durationMs + " ms]";
        }
    }

    *//* --------------------------------------------------------------------- *
     *  Simulated remote services                                            *
     * --------------------------------------------------------------------- *//*
    private static final Random RAND = new Random();

    *//** Simulates a call that takes 0‑2 seconds and returns a string. *//*
    private static Result fetchUserInfo(int userId) {
        sleepRandom(500, 1500);
        return new Result("UserInfo{id=" + userId + "}", System.currentTimeMillis());
    }

    *//** Simulates a call that takes 0‑2 seconds and returns a string. *//*
    private static Result fetchOrders(int userId) {
        sleepRandom(800, 2000);
        return new Result("Orders{userId=" + userId + "}", System.currentTimeMillis());
    }

    *//** Simulates a call that takes 0‑3 seconds and may throw an exception. *//*
    private static Result calculateDiscount(Result userInfo, Result orders) {
        sleepRandom(300, 1200);
        // 20 % chance to fail → useful for exception handling demo
        if (RAND.nextInt(10) < 2) {
            throw new IllegalStateException("Discount service is temporarily unavailable");
        }
        return new Result("Discount{10%}", System.currentTimeMillis());
    }

    *//** Helper that sleeps a random amount of milliseconds. *//*
    private static void sleepRandom(int minMs, int maxMs) {
        int delay = RAND.nextInt(maxMs - minMs + 1) + minMs;
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            // propagate interrupt status – caller will notice
            Thread.currentThread().interrupt();
            throw new CompletionException(e);
        }
    }

    *//* --------------------------------------------------------------------- *
     *  Demo entry point                                                    *
     * --------------------------------------------------------------------- *//*
    public static void main(String[] args) throws Exception {
        LOG.info("=== CompletableFuture demo start ===");

        // 1️⃣  Simple async supply + thenApply
        demoThenApply();

        // 2️⃣  Async composition: thenCompose (flatten)
        demoThenCompose();

        // 3️⃣  Combine two independent futures: thenCombine
        demoThenCombine();

        // 4️⃣  Wait for many futures: allOf  & anyOf
        demoAllOfAndAnyOf();

        // 5️⃣  Exception handling (exceptionally / handle / whenComplete)
        demoExceptionHandling();

        // 6️⃣  Timeout & cancellation
        demoTimeoutAndCancel();

        // 7️⃣  Graceful shutdown of our own executor
        LOG.info("Shutting down custom thread‑pool …");
        POOL.shutdown();
        POOL.awaitTermination(5, TimeUnit.SECONDS);
        LOG.info("=== CompletableFuture demo finished ===");
    }

    *//* --------------------------------------------------------------------- *
     *  1️⃣ thenApply – transform the result of a previous stage               *
     * --------------------------------------------------------------------- *//*
    private static void demoThenApply() {
        LOG.info("\n--- demoThenApply ----------------------------------------------------");
        CompletableFuture<Result> future = CompletableFuture
                .supplyAsync(() -> fetchUserInfo(42), POOL)          // ← async supplier
                .thenApply(userInfo -> {
                    LOG.info("Transforming userInfo → " + userInfo);
                    // Just append extra info – no extra async work
                    return new Result(userInfo.description() + " (verified)", System.currentTimeMillis());
                });

        LOG.info("Result of thenApply: " + future.join()); // join blocks *only* here
    }

    *//* --------------------------------------------------------------------- *
     *  2️⃣ thenCompose – flatten async steps (like flatMap)                  *
     * --------------------------------------------------------------------- *//*
    private static void demoThenCompose() {
        LOG.info("\n--- demoThenCompose -------------------------------------------------");
        CompletableFuture<Result> composed = CompletableFuture
                .supplyAsync(() -> fetchUserInfo(7), POOL) // first async step
                .thenCompose(userInfo ->                     // ← returns another CF
                        CompletableFuture.supplyAsync(() -> fetchOrders(7), POOL)
                                .thenApply(orders -> {
                                    LOG.info("Got both userInfo & orders, building summary");
                                    return new Result(
                                            "Summary{" + userInfo.description() + "," + orders.description() + "}",
                                            System.currentTimeMillis());
                                })
                );

        LOG.info("Result of thenCompose: " + composed.join());
    }

    *//* --------------------------------------------------------------------- *
     *  3️⃣ thenCombine – run two futures in parallel and combine the result *
     * --------------------------------------------------------------------- *//*
    private static void demoThenCombine() {
        LOG.info("\n--- demoThenCombine -------------------------------------------------");
        CompletableFuture<Result> userInfoFuture = CompletableFuture.supplyAsync(() -> fetchUserInfo(11), POOL);
        CompletableFuture<Result> ordersFuture   = CompletableFuture.supplyAsync(() -> fetchOrders(11), POOL);

        CompletableFuture<Result> combined = userInfoFuture
                .thenCombine(ordersFuture,
                        (userInfo, orders) -> {
                            LOG.info("Both branches finished – now combine");
                            return new Result(
                                    "UserOrders{" + userInfo.description() + "|" + orders.description() + "}",
                                    System.currentTimeMillis());
                        });

        LOG.info("Result of thenCombine: " + combined.join());
    }

    *//* --------------------------------------------------------------------- *
     *  4️⃣ allOf / anyOf – wait for many futures                           *
     * --------------------------------------------------------------------- *//*
    private static void demoAllOfAndAnyOf() {
        LOG.info("\n--- demoAllOfAndAnyOf ---------------------------------------------");
        // Create a list of 5 independent futures (simulating 5 remote calls)
        List<CompletableFuture<Result>> futures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int id = i;
            futures.add(CompletableFuture.supplyAsync(() -> fetchUserInfo(id), POOL));
        }

        // allOf – wait for *all* of them to finish
        CompletableFuture<Void> allDone = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allDone.thenRun(() -> LOG.info("All 5 user‑info calls completed"));
        allDone.join(); // block only for demo purposes

        // anyOf – proceed as soon as *any* completes
        CompletableFuture<Object> first = CompletableFuture.anyOf(futures.toArray(new CompletableFuture[0]));
        LOG.info("First completed future: " + ((Result) first.join()));
    }

    *//* --------------------------------------------------------------------- *
     *  5️⃣ Exception handling – several ways to react to failures           *
     * --------------------------------------------------------------------- *//*
    private static void demoExceptionHandling() {
        LOG.info("\n--- demoExceptionHandling -------------------------------------------");

        // Build a pipeline that *may* throw an exception in calculateDiscount()
        CompletableFuture<Result> pipeline = CompletableFuture
                .supplyAsync(() -> fetchUserInfo(99), POOL)
                .thenCombineAsync(
                        CompletableFuture.supplyAsync(() -> fetchOrders(99), POOL),
                        (userInfo, orders) -> {
                            LOG.info("Calling discount service …");
                            return calculateDiscount(userInfo, orders); // may throw
                        },
                        POOL);

        // 1️⃣ exceptionally – supply a fallback value if *any* exception occurs
        CompletableFuture<Result> withFallback = pipeline.exceptionally(ex -> {
            LOG.warning("Discount failed: " + ex.getCause());
            return new Result("Discount{fallback=0%}", System.currentTimeMillis());
        });

        LOG.info("Result after exceptionally: " + withFallback.join());

        // 2️⃣ handle – lets you see both result *and* exception and decide what to return
        CompletableFuture<Result> handled = pipeline.handle((res, ex) -> {
            if (ex != null) {
                LOG.warning("handle sees exception: " + ex.getCause());
                return new Result("Discount{handled=0%}", System.currentTimeMillis());
            }
            return res; // success path
        });

        LOG.info("Result after handle: " + handled.join());

        // 3️⃣ whenComplete – a side‑effect hook that receives result *or* exception,
        //    but does NOT change the eventual outcome.
        CompletableFuture<Result> sideEffect = pipeline.whenComplete((res, ex) -> {
            if (ex != null) {
                LOG.severe("whenComplete observed failure: " + ex.getCause());
            } else {
                LOG.info("whenComplete observed success: " + res);
            }
        });

        // We still need to join to make the demo sequential
        try {
            LOG.info("Result after whenComplete: " + sideEffect.join());
        } catch (CompletionException ce) {
            LOG.warning("Pipeline finally failed (as expected for demo): " + ce.getCause());
        }
    }

    *//* --------------------------------------------------------------------- *
     *  6️⃣ Timeout & explicit cancellation                                   *
     * --------------------------------------------------------------------- *//*
    private static void demoTimeoutAndCancel() {
        LOG.info("\n--- demoTimeoutAndCancel -------------------------------------------");

        // A future that deliberately sleeps for 5 seconds
        CompletableFuture<Result> longRunning = CompletableFuture.supplyAsync(() -> {
            LOG.info("Long‑running job started");
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOG.warning("Long‑running job was interrupted");
                throw new CompletionException(e);
            }
            LOG.info("Long‑running job completed");
            return new Result("LongJob", System.currentTimeMillis());
        }, POOL);

        // ① Apply a timeout – after 2 seconds we complete with a fallback value
        CompletableFuture<Result> withTimeout = longRunning
                .completeOnTimeout(new Result("TimeoutFallback", System.currentTimeMillis()),
                        Duration.ofSeconds(2));

        LOG.info("Result after timeout (should be fallback): " + withTimeout.join());

        // ② Explicit cancellation (illustrating that a cancelled CF will throw CancellationException)
        CompletableFuture<Result> cancellable = CompletableFuture.supplyAsync(() -> {
            LOG.info("Cancellable job started");
            try {
                Thread.sleep(4_000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOG.warning("Cancellable job interrupted");
                throw new CompletionException(e);
            }
            return new Result("CancellableJob", System.currentTimeMillis());
        }, POOL);

        // Cancel after 1 second
        POOL.schedule(() -> {
            LOG.info("Attempting to cancel the job …");
            boolean cancelled = cancellable.cancel(true); // true → may interrupt if running
            LOG.info("Cancellation result = " + cancelled);
        }, 1, TimeUnit.SECONDS);

        try {
            // This join will throw CancellationException because the future was cancelled
            cancellable.join();
        } catch (CancellationException ce) {
            LOG.warning("Caught expected CancellationException: " + ce);
        }
    }*/
}
