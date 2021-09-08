package javaparactice.concurrency.practice1.serialnotifier;

import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompletedTaskThread extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompletedTaskThread.class);

    private LinkedBlockingQueue<Task> completedTaskQueue;

    public CompletedTaskThread(LinkedBlockingQueue<Task> completedTaskQueue) {
        this.completedTaskQueue = completedTaskQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task completedTask = completedTaskQueue.take();
                LOGGER.info("Completed Task with id : {}", completedTask.id);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
