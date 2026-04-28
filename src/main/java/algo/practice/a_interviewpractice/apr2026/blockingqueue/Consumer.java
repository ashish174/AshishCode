package algo.practice.a_interviewpractice.apr2026.blockingqueue;

import java.util.Deque;

public class Consumer extends Thread{
    CustomBlockingQueue<CustomBlockingQueue.Task> queue;
    String name;

    public Consumer(CustomBlockingQueue<CustomBlockingQueue.Task> queue, String name){
        this.queue = queue;
        this.name = name;
    }

    public void run(){
        while(true){
            try {
                CustomBlockingQueue.Task task = queue.remove();
                if(task==null){
                    System.out.printf("Queue is closed. Consumer %s Exitting \n", name);
                    return;
                }
                task.execute();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
