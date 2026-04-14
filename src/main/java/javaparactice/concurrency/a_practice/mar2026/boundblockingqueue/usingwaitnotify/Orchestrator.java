package javaparactice.concurrency.a_practice.mar2026.boundblockingqueue.usingwaitnotify;

import java.util.Random;

public class Orchestrator {

    public static void main(String[] args) throws InterruptedException {
        // The queue capacity is intentionally small so blocking behavior is easy to observe.
        int queueSize = 5;
        BoundedBlockingQueue<Task> taskQueue = new BoundedBlockingQueue<>(queueSize);
        Random random = new Random();

        // Create multiple producers to demonstrate contention on the queue.
        Producer producer1 = new Producer("producer-1", taskQueue, random.nextInt(50));
        Producer producer2 = new Producer("producer-2", taskQueue, random.nextInt(30));
        Producer producer3 = new Producer("producer-3", taskQueue, random.nextInt(20));

        // Create multiple consumers to demonstrate concurrent draining.
        Worker worker1 = new Worker("worker-1", taskQueue);
        Worker worker2 = new Worker("worker-2", taskQueue);

        // Start consumers first so they are ready to drain immediately.
        worker1.start();
        worker2.start();

        // Start all producers concurrently.
        producer1.start();
        producer2.start();
        producer3.start();

        // Wait for all producers to finish publishing their work.
        producer1.join();
        producer2.join();
        producer3.join();

        // Closing tells workers that no future tasks will arrive.
        taskQueue.close();

        // Wait for workers to drain remaining work and exit.
        worker1.join();
        worker2.join();
    }
}
