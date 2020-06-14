package javaparactice.concurrency.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {
    public static Logger logger = LoggerFactory.getLogger(FutureDemo.class);
    public static void main(String[] args) throws InterruptedException {
        MDC.put("mdc", "[Ashish]");
        logger.info("Hiii");
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Future<Long> future = threadPool.submit(() -> {
            MDC.put("mdc", MDC.get("mdc")+"[ThreadId:"+Thread.currentThread().getId()+"]");
            Thread.sleep(1000);
            long time = new Date().getTime();
            logger.info("Time {}", time);
            return time;
        });
        MDC.put("mdc", MDC.get("mdc")+"[ThreadId:"+Thread.currentThread().getId()+"]");

        while(!future.isDone()){
            logger.info("Future is not finished Yet.");
            Thread.sleep(100);
        }
        try {
            logger.info("Future Result "+ future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        threadPool.shutdown();
    }


}
