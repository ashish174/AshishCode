package javaparactice.concurrency.customthreadpool.mar2026;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadScheduler {
    Queue<Task> queue;
    List<Worker> workerPool;
    Object queueLock;
    int capacity = 2;
    AtomicBoolean shutDown;

    public ThreadScheduler(){
        queue = new PriorityQueue<>((t1, t2) -> t2.priority - t1.priority );
        workerPool = new ArrayList<>(capacity);
        shutDown = new AtomicBoolean(false);
        queueLock = new Object();
    }

    public AtomicBoolean getShutDown(){
        return this.shutDown;
    }

    public void start(){
        for(int i=0; i<capacity; i++){
            Worker worker = new Worker(this) ;
            workerPool.add(worker);
            worker.start();
        }
    }

    public void stop(){
        shutDown.set(true);
    }

    public void enqueue(Task task){
        synchronized(queueLock) {
            System.out.printf("******Enqueuing Task %d, Priority %d \n", task.taskId, task.priority);
            queue.add(task);
            queueLock.notifyAll();
        }
    }

    public Task dequeue(){
        synchronized(queueLock) {
            while(queue.isEmpty()){
                // System.out.println("Underflow");
                try{
                    queueLock.wait();
                } catch(InterruptedException e){
                    throw new RuntimeException("Thread Interrupted"+e);
                }
            }
            Task task  = queue.poll();
            System.out.printf("######Dequeuing Task %d, Priority %d \n", task.taskId, task.priority);
            return task;
        }
    }


}
