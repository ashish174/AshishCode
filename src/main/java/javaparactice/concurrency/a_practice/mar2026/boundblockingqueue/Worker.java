package javaparactice.concurrency.a_practice.mar2026.boundblockingqueue;

import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class Worker extends Thread {
    private Queue<Task> taskQueue;
    private int queueSize;

    public Worker(Queue<Task> taskQueue, int queueSize) {
        this.taskQueue = taskQueue;
        this.queueSize = queueSize;
    }

    public void run(){
        while(true) {
            Task task = pollTask();
            task.execute();
        }
    }

    public Task pollTask() {
        synchronized (taskQueue) {
            //Underflow check
            while(taskQueue.isEmpty()){
                try {
                    taskQueue.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Task task = taskQueue.poll();
            taskQueue.notify();
            return task;
        }
    }


}
