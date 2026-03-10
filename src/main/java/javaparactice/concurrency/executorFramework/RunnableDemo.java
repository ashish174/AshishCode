package javaparactice.concurrency.executorFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * // For scheduling initial delay or fix interval
 * ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
 *
 * To run after initial delay
 * // schedule(task, initialDelay, unit)
 * scheduler.schedule(() -> {
 *     System.out.println("Executed after 5 seconds");
 * }, 5, TimeUnit.SECONDS);
 *
 * To run at fixed schedule
 * // scheduleAtFixedRate(task, initialDelay, period, unit)
 * scheduler.scheduleAtFixedRate(() -> {
 *     sendHeartbeat();
 * }, 0, 10, TimeUnit.SECONDS);
 *
 * To run with fixed gap from last execution
 * // scheduleWithFixedDelay(task, initialDelay, delay, unit)
 * scheduler.scheduleWithFixedDelay(() -> {
 *     cleanUpTempFiles();
 * }, 0, 5, TimeUnit.MINUTES);
 *
 *
 *
 **/
public class RunnableDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        try {
            for (int i = 0; i < 10; i++) {
                executorService.submit(new RunnableTask(i));
            }
        } finally {
            executorService.shutdown();
        }

        try {
            if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

/*    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i = 0; i<10; i++){
            executorService.submit(new RunnableTask(i));
        }
        executorService.shutdown();
    }*/
}
