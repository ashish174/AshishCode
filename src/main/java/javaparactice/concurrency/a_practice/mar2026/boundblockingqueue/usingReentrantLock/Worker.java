package javaparactice.concurrency.a_practice.mar2026.boundblockingqueue.usingReentrantLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Worker extends Thread {
    // Shared lock-based queue from which the worker consumes tasks.
    private final BoundedBlockingQueue<Task> taskQueue;

    public Worker(String name, BoundedBlockingQueue<Task> taskQueue) {
        super(name);
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        // Keep consuming until the queue signals that no future tasks will arrive.
        while (true) {
            try {
                Task task = taskQueue.take();
                // Null means the queue is closed and completely drained.
                if (task == null) {
                    log.info("Worker exiting: {}", getName());
                    return;
                }
                // Execute one task outside the queue implementation.
                task.execute();
            } catch (InterruptedException e) {
                // Preserve the interrupt and terminate the worker.
                Thread.currentThread().interrupt();
                log.info("Worker interrupted: {}", getName());
                return;
            }
        }
    }
}
