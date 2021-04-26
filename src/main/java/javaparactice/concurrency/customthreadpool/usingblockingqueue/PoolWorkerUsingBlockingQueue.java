package javaparactice.concurrency.customthreadpool.usingblockingqueue;

import java.util.concurrent.LinkedBlockingQueue;

public class PoolWorkerUsingBlockingQueue extends Thread {
    private LinkedBlockingQueue<Runnable> queue;

    public PoolWorkerUsingBlockingQueue(LinkedBlockingQueue<Runnable> taskQueue) {
        queue = taskQueue;
    }

    @Override
    public void run() {
        try {
            Runnable task = queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
