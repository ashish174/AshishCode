package javaparactice.concurrency.customthreadpool;

import java.util.concurrent.LinkedBlockingQueue;

public class PoolWorker extends Thread {
    private LinkedBlockingQueue queue;

    public PoolWorker(LinkedBlockingQueue taskQueue) {
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
                task = (Task) queue.poll();
            }
            try {
                task.run();
            } catch (RuntimeException e) {
                System.out.println("Thread pool is interrupted due to an issue: " + e.getMessage());
            }

        }
    }
}
