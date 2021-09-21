package javaparactice.concurrency.practice1.serialnotifier;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerThread extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerThread.class);

    LinkedBlockingQueue<Task> taskQueue;
    private LinkedBlockingQueue<Task> completedTaskQueue;

    public WorkerThread(LinkedBlockingQueue<Task> taskQueue, LinkedBlockingQueue<Task> completedTaskQueue) {
        this.taskQueue = taskQueue;
        this.completedTaskQueue = completedTaskQueue;
    }

    public void run() {
        while (true) {
            Task task = null;
            try {
                task = taskQueue.take();
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                Future<Integer> future = executorService.submit(task);
                Integer result = future.get();
                completedTaskQueue.put(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            task.printValue();
        }
    }
}
