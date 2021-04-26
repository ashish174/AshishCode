package javaparactice.concurrency.ProducerConsumerDetailed;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerWithWaitNotify {
  public static void main(String[] args) {
    final Integer max_Size = 5;
    List<Integer> sharedTaskList = new ArrayList<>(max_Size);
    //Queue<Integer> sharedQueue = new LinkedList<Integer>(5);
    //2 Producer Thread and 1 Consumer Thread
    Thread producerThread1 = new Thread(new Producer(sharedTaskList, max_Size), "Producer1");
    Thread producerThread2 = new Thread(new Producer(sharedTaskList, max_Size), "Producer2");
    Thread consumerThread = new Thread(new Consumer(sharedTaskList, max_Size), "Consumer");
    producerThread1.start();
    producerThread2.start();
    consumerThread.start();
  }
}
