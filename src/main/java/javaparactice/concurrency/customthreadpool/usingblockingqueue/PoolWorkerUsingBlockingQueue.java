package javaparactice.concurrency.customthreadpool.usingblockingqueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Worker class extend thread so as to execute in a new thread
 */
public class PoolWorkerUsingBlockingQueue extends Thread {
    //Alternatively we can also use ArrayBlockingQueue
    private LinkedBlockingQueue<Runnable> queue;

    public PoolWorkerUsingBlockingQueue(LinkedBlockingQueue<Runnable> taskQueue) {
        queue = taskQueue;
    }

    @Override
    public void run() {
        Runnable task;
        while (true) {
            try {
                task = queue.take();
                task.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }


    }
}
