package javaparactice.concurrency.a_practice.mar2026.boundblockingqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Orchestrator {

    public static void main(String[] args){
        Queue<Task> taskQueue = new LinkedList<>();
        int queueSize = 5;
        Producer producer1 = new Producer(taskQueue, queueSize);
        Producer producer2 = new Producer(taskQueue, queueSize);
        Producer producer3 = new Producer(taskQueue, queueSize);

        Worker worker1 = new Worker(taskQueue, queueSize);
        Worker worker2 = new Worker(taskQueue, queueSize);

        Random random = new Random();

        worker1.start();
        worker2.start();

        producer1.start();
        producer2.start();
        producer3.start();

    }
}
