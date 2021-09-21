package javaparactice.concurrency.completionfuture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * To simply execute some code asynchronously
 * runAsync/supplyAsync :- create a CompletableFuture instance out of Runnable and Supplier functional types correspondingly.
 * Here Runnable(not return value) & Supplier(return value) are functional Interface whose implementation through lambda function, we use.
 * thenApply/thenAccept/thenRun :- to chain callbacks for further operation on Completable future and return completable future
 * thenCompose/thenCombine :-
 * allOf/anyOf :- to wait for all of them to execute and then process their combined results.
 * handle :-
 *
 */
public class CompletionFutureDemo {
    public static void main(String[] args) {
        int taskSize = 50;
        Random randomIntGen = new Random();
        List<CompletableFuture<Integer>> completableFutureList = new ArrayList<>();
        /*for(int i = 0; i < taskSize; i++){
            Task task = new Task(randomIntGen.nextInt(50));
            CompletableFuture<Integer> completableFuture = CompletableFuture
                    .supplyAsync(() -> task.execute())
                    .thenCompose(() -> )
            completableFutureList.add(completableFuture);
        }
        completableFutureList*/
    }
}
