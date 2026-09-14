package javaparactice.concurrency.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Category	-> Method -> 	Purpose
 * Creation
 *          supplyAsync(Supplier)	Starts an async task that returns a value.
 * 	        runAsync(Runnable)	Starts an async task that returns nothing (Void).
 * Chaining
*           thenApply(Function)	Map: Transforms the result (e.g., String to Integer).
 * 	        thenCompose(Function)	FlatMap: Use the result to start another async task.
 * Consumption
 *          thenAccept(Consumer)	Uses the result but returns nothing (e.g., logging).
 * 	        thenRun(Runnable)	Runs something after completion, ignoring the result.
 * Combining
 *          thenCombine(Other, BiFunc)	Waits for two specific futures and combines results.
 * 	        allOf(Futures...)	Waits for all provided futures to finish.
 * Exceptions
 *          exceptionally(Func)	Provides a fallback value if an error occurs.
 * 	        handle(BiFunc)	Catch-all for both result AND exception.
 * Completion
 *          join()	Like get(), but throws Unchecked exceptions (cleaner code).
 * 	        complete(value)	Manually finishes the future (useful for testing/mocks).
 *
 *
 *
 *
 * **/
public class CompletableFutureDemo_old {
    private static Logger logger = LoggerFactory.getLogger(CompletableFutureDemo_old.class);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CompletableFuture<Integer> completableFuture = CompletableFuture
            .supplyAsync(() -> Factorial.findFactorial(35), threadPool);
        while(!completableFuture.isDone()){
            logger.info("Task Not Completed.");
        }
        logger.info("Factorial : {}", completableFuture.get());
    }
}
