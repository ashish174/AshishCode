package javaparactice.concurrency.yield;

public class ThreadYield {
  public static void main(String[] args) {
    Thread producer = new Producer();
    Thread consumer = new Consumer();
    producer.setName("ProducerThread");
    producer.setPriority(Thread.MIN_PRIORITY);
    consumer.setName("ConsumerThread");
    consumer.setPriority(Thread.MAX_PRIORITY);
    producer.start();
    consumer.start();
    System.out.println("hibernateORM.Main Thread Done");
  }
}

