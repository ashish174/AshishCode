package javaparactice.concurrency.practice1.executors;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerThread extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerThread.class);

    LinkedBlockingQueue<Task> taskQueue;

    public WorkerThread(LinkedBlockingQueue<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void run() {
        while (true) {
            Task task = null;
            try {
                task = taskQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            task.printValue();
        }
    }
}
