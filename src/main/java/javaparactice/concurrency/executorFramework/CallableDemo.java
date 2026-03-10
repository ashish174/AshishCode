package javaparactice.concurrency.executorFramework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
public class CallableDemo {
    public static Logger logger = LoggerFactory.getLogger(CallableDemo.class);

    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<String>> futureList = new ArrayList<>();

        try {
            for (int i = 0; i < 10; i++) {
                futureList.add(executorService.submit(new CallableTask(i)));
            }
        } finally {
            executorService.shutdown(); // stop accepting new tasks
        }

        try {
            if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                executorService.shutdownNow(); // force shutdown
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt(); // restore interrupt
        }

        for (Future<String> future : futureList) {
            try {
                logger.info("Value : {}", future.get());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.warn("Interrupted while getting result", e);
            } catch (ExecutionException e) {
                logger.error("Task execution failed", e.getCause());
            }
        }

    }
/*    public static void main(String[] args) {
        try{
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            List<Future<String>> futureList = new ArrayList<>();
            for(int i = 0; i<10; i++){
                Future<String> future = executorService.submit(new CallableTask(i));
                futureList.add(future);
            }
            executorService.shutdown();
            while (!executorService.isTerminated()){
                executorService.awaitTermination(2000, TimeUnit.MILLISECONDS);
            }
            if(executorService.isTerminated()){

                for(Future future : futureList){
                    logger.info("Value : {}", future.get());
                }


            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }*/
}
