package algo.practice.a_interviewpractice.apr2026.blockingqueue;

import java.util.Deque;

public class Producer extends Thread{
    CustomBlockingQueue<CustomBlockingQueue.Task> queue;
    String name;

    public Producer(CustomBlockingQueue<CustomBlockingQueue.Task> queue, String name){
        this.queue = queue;
        this.name = name;
    }

    public void run(){
        for(int i=0; i<10; i++){
            CustomBlockingQueue.Task task = new CustomBlockingQueue.Task(name+": Task"+i);
            try {
                queue.add(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            } catch (IllegalStateException e){
                System.out.printf("Queue is closed. Producer %s Exitting \n", name);
                return;
            }
        }
    }
}
