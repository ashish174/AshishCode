package concurrency.ProducerConsumerDetailed;

import java.util.List;
import java.util.Random;

public class Producer implements Runnable{
  List<Integer> taskQueue;
  int Max_Size;

  public Producer(List<Integer> taskQueue, int max_Size) {
    this.taskQueue = taskQueue;
    Max_Size = max_Size;
  }

  @Override
  public void run() {
    while (true){
      produce();
    }

  }

  void produce(){
    synchronized (taskQueue){
      if(taskQueue.size()==Max_Size){
        try {
          System.out.println(Thread.currentThread().getName()+" Waiting as TaskQueue full");
          taskQueue.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      int task = new Random().nextInt(1000);
      taskQueue.add(task);
      System.out.println(Thread.currentThread().getName()+" Adding Task "+task+" .TaskQueue "+taskQueue);
      taskQueue.notifyAll();
    }
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
