package javaparactice.concurrency.a_practice.mar2026.boundblockingqueue;

import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.Random;

@Slf4j
public class Producer extends Thread {
    Queue<Task> taskQueue;
    int queueSize;
    Random rand;
    public Producer(Queue<Task> taskQueue, int queueSize) {
        this.taskQueue = taskQueue;
        this.queueSize = queueSize;
        rand = new Random();
    }

    public void run(){
        startPublishingTask(rand.nextInt(50));
    }

    public void addTask(Task task){
        synchronized (taskQueue){
            //Overflow check
            while(taskQueue.size() >= queueSize){
                try {
                    taskQueue.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            taskQueue.add(task);
            taskQueue.notify();
        }
    }

    public void startPublishingTask(int numOfTasks){
        for(int i=0; i<numOfTasks; i++){
            Task task = new Task(rand.nextInt(500));
            log.info("Publishing Task : {}", task.taskId);
            addTask(task);
        }
    }
}
