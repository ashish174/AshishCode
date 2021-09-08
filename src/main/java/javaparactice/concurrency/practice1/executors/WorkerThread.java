package javaparactice.concurrency.practice1.executors;

import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerThread extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerThread.class);

    Queue<Task> taskQueue;

    public WorkerThread(Queue<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void run() {
        while (true) {
            Task task = taskQueue.remove();
            task.printValue();
        }
    }
}
