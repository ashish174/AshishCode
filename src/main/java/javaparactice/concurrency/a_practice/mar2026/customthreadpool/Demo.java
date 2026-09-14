package javaparactice.concurrency.a_practice.mar2026.customthreadpool;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 30-minute interview level custom priority thread pool demo.
 */
public class Demo {
    public static void main(String[] args) {
        ThreadScheduler scheduler = new ThreadScheduler(3);
        scheduler.start();

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            Task task = new Task(i, random.nextInt(10));
            scheduler.submit(task);
        }

        scheduler.shutdown();
        try {
            boolean terminated = scheduler.awaitTermination(20, TimeUnit.SECONDS);
            System.out.println("Scheduler terminated: " + terminated);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread interrupted while waiting for termination");
        }
    }
}
