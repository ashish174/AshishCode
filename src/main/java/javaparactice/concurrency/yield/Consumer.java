package javaparactice.concurrency.yield;

class Consumer extends Thread{
  public void run(){
    for (int i = 0; i < 5; i++) {
      System.out.println("Consumer: Consumed Item - "+i);
      Thread.yield();
    }
  }
}
