package javaparactice.concurrency.ProducerConsumerDetailed;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerWithWaitNotify {
  public static void main(String[] args) {
    final Integer max_Size = 5;
    List<Integer> sharedTaskList = new ArrayList<>(max_Size);
    //Queue<Integer> sharedQueue = new LinkedList<Integer>(5);
    Thread producerThread = new Thread(new Producer(sharedTaskList, max_Size), "Producer");
    Thread consumerThread = new Thread(new Consumer(sharedTaskList, max_Size), "Consumer");
    producerThread.start();
    consumerThread.start();
  }
}
