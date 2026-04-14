package javaparactice.concurrency.a_practice.mar2026.boundblockingqueue.usingReentrantLock;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class Producer extends Thread {
    // Shared lock-based queue used by all producers and workers.
    private final BoundedBlockingQueue<Task> taskQueue;
    // Each producer publishes a fixed number of tasks.
    private final int numOfTasks;
    // Random is used only to generate demo task ids.
    private final Random rand;

    public Producer(String name, BoundedBlockingQueue<Task> taskQueue, int numOfTasks) {
        super(name);
        this.taskQueue = taskQueue;
        this.numOfTasks = numOfTasks;
        this.rand = new Random();
    }

    @Override
    public void run() {
        // Publish a bounded number of tasks into the queue.
        for (int i = 0; i < numOfTasks; i++) {
            Task task = new Task(rand.nextInt(500));
            log.info("Publishing task {} from {}", task.getTaskId(), getName());
            try {
                taskQueue.put(task);
            } catch (InterruptedException e) {
                // Restore interrupt status and exit rather than hiding cancellation.
                Thread.currentThread().interrupt();
                log.info("Producer interrupted: {}", getName());
                return;
            }
        }
    }
}
