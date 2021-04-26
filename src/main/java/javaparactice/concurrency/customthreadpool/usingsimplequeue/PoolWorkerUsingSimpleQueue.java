package javaparactice.concurrency.customthreadpool.usingsimplequeue;

import java.util.Queue;
import javaparactice.concurrency.customthreadpool.Task;

public class PoolWorkerUsingSimpleQueue extends Thread {
    private Queue<Runnable> queue;

    public PoolWorkerUsingSimpleQueue(Queue<Runnable> taskQueue) {
        queue = taskQueue;
    }

    @Override
    public void run() {
        Runnable task;
        while (true) {
            synchronized (queue) {
                if (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                task = queue.poll();
            }
            try {
                task.run();
            } catch (RuntimeException e) {
                System.out.println("Thread pool is interrupted due to an issue: " + e.getMessage());
            }

        }
    }
}
