package javaparactice.concurrency.customthreadpool.mar2026;

import java.util.Random;

/**
* Task
* Worker Threads
* Executor framework : List<Thread>, start(), stop()
* Demo class for psvm, create and add new tasks
*/
public class Demo {
    public static void main(String[] args){
        ThreadScheduler scheduler = new ThreadScheduler();
        scheduler.start();
        Random random = new Random();
        for(int i=0; i < 30; i++){
            try{
                Thread.sleep(2000);
            } catch(InterruptedException e){
                throw new RuntimeException("Sleep interrupted", e);
            }
            Task task = new Task(i, random.nextInt(10));
            scheduler.enqueue(task);
        }
        scheduler.stop();
    }


}
