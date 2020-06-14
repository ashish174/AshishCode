package javaparactice.concurrency.ProducerConsumerDetailed;

import java.util.List;

public class Consumer implements Runnable{
  List<Integer> taskQueue;
  int Max_Size;

  public Consumer(List<Integer> taskQueue, int max_Size) {
    this.taskQueue = taskQueue;
    Max_Size = max_Size;
  }

  @Override
  public void run() {
    while (true){
      consume();
    }

  }

  void consume(){
    synchronized (taskQueue){
      if(taskQueue.isEmpty()){
        try {
          System.out.println(Thread.currentThread().getName()+" Waiting as TaskQueue Empty");
          taskQueue.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      int task = taskQueue.remove(0);
      System.out.println(Thread.currentThread().getName()+" Removing Task "+task+" .TaskQueue "+taskQueue);

      taskQueue.notifyAll();

    }
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
