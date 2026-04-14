package javaparactice.concurrency.a_practice.mar2026.boundblockingqueue.usingwaitnotify;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Worker extends Thread {
    // Shared queue from which workers consume tasks.
    private final BoundedBlockingQueue<Task> taskQueue;

    public Worker(String name, BoundedBlockingQueue<Task> taskQueue) {
        super(name);
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        // Workers keep consuming until the queue signals end-of-stream.
        while (true) {
            try {
                Task task = taskQueue.take();
                // Null means the queue is closed and drained.
                if (task == null) {
                    log.info("Worker exiting: {}", getName());
                    return;
                }
                task.execute();
            } catch (InterruptedException e) {
                // Preserve interrupt status and stop the worker.
                Thread.currentThread().interrupt();
                log.info("Worker interrupted: {}", getName());
                return;
            }
        }
    }
}
