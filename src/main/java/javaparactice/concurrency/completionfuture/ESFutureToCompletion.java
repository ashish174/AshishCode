package javaparactice.concurrency.completionfuture;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ESFutureToCompletion {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);
        Callable<String> callable = null;
        CompletableFuture<String> cs = new CompletableFuture<>();
        es.submit(new Runnable() {
            @Override
            public void run() {
                String returnVal = null;
                try {
                    returnVal = callable.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cs.complete(returnVal);
            }
        });
        cs.thenAccept(returnVal -> {
            System.out.println(returnVal);
        });
    }

}
