package javaparactice.concurrency.completionfuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * To simply execute some code asynchronously
 * runAsync/supplyAsync :- create a CompletableFuture instance out of Runnable and Supplier functional types correspondingly.
 * Here Runnable(not return value) & Supplier(return value) are functional Interface whose implementation through lambda function, we use.
 * thenApply/thenAccept/thenRun :- to chain callbacks for further operation on Completable future and return completable future
 * thenCompose/thenCombine :-
 * allOf/anyOf :- to wait for all of them to execute and then process their combined results.
 * handle :-
 */
public class CompletionFutureDemo {
    public static final Logger LOGGER = LoggerFactory.getLogger(CompletionFutureDemo.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int taskSize = 5;
        Random randomIntGen = new Random();
        List<CompletableFuture<Integer>> completableFutureList = new ArrayList<>();
        for (int i = 0; i < taskSize; i++) {
            Task task = new Task(randomIntGen.nextInt(10));
            CompletableFuture<Integer> completableFuture = CompletableFuture
                    .supplyAsync(() -> task.execute())
                    .thenApply(duration -> {
                        LOGGER.info("[{}] Thread returned Duration {}", Thread.currentThread().getName(), duration);
                        return duration;
                    });
            completableFutureList.add(completableFuture);
        }
        // Combine all future, so as it will be easy to found out when all job completes
        // this will be used to attach callbacks
        CompletableFuture<Void> combinedFuture = CompletableFuture
                .allOf(completableFutureList.toArray(new CompletableFuture[completableFutureList.size()]));
        // once the job finishes we want to join all result into a list, Ex: GetDevices
        // so we can use combinedall future and attach callback on that so to operate when all job finishes
        CompletableFuture<List<Integer>> combinedCFasList = combinedFuture.thenApply(future -> {
            return completableFutureList.stream()
                    .map(cf -> cf.join())
                    .collect(Collectors.toList());
        });
        List<Integer> resultDurationList = combinedCFasList.get();
        LOGGER.info("Result Duration List : {}", resultDurationList);
        LOGGER.info("Main Thread finishes");
    }
}
