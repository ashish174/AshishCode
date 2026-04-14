package javaparactice.concurrency.a_practice.mar2026.boundblockingqueue.usingReentrantLock;

import java.util.Random;

public class Orchestrator {

    public static void main(String[] args) throws InterruptedException {
        // A small capacity makes blocking behavior easy to observe in logs.
        int queueSize = 5;
        BoundedBlockingQueue<Task> taskQueue = new BoundedBlockingQueue<>(queueSize);
        Random random = new Random();

        // Multiple producers create contention on the queue's put path.
        Producer producer1 = new Producer("producer-1", taskQueue, random.nextInt(50));
        Producer producer2 = new Producer("producer-2", taskQueue, random.nextInt(30));
        Producer producer3 = new Producer("producer-3", taskQueue, random.nextInt(20));

        // Multiple workers create contention on the queue's take path.
        Worker worker1 = new Worker("worker-1", taskQueue);
        Worker worker2 = new Worker("worker-2", taskQueue);

        // Start workers first so they are ready to consume immediately.
        worker1.start();
        worker2.start();

        // Start all producers concurrently.
        producer1.start();
        producer2.start();
        producer3.start();

        // Wait until all producers have published their tasks.
        producer1.join();
        producer2.join();
        producer3.join();

        // Closing tells workers that the queue will eventually drain to completion.
        taskQueue.close();

        // Wait for the remaining queued tasks to be consumed.
        worker1.join();
        worker2.join();
    }
}
