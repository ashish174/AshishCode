package javaparactice.concurrency.customthreadpool.mar2026;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Worker extends Thread {
    private AtomicBoolean shutDown;
    private ThreadScheduler scheduler;
    public Worker(ThreadScheduler scheduler){
        this.scheduler = scheduler;
        this.shutDown = scheduler.getShutDown();
    }

    public void run(){
        while(!shutDown.get()){
            Task task = scheduler.dequeue();
            task.execute();
            try{
                Thread.sleep(2000);
            } catch(InterruptedException e){
                System.out.println("Interrupted, exiting");
                throw new RuntimeException("Encountered Interruption");
            }

        }

    }
}
