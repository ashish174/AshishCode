package javaparactice.concurrency.executorFramework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunnableTask implements Runnable {
    public static Logger logger = LoggerFactory.getLogger(RunnableTask.class);
    private int sequence;

    public RunnableTask(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public void run() {
        logger.info("Thread {} Started with Task {}.", Thread.currentThread().getName(), sequence);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Thread {} Completed with Task {}.", Thread.currentThread().getName(), sequence);

    }
}
