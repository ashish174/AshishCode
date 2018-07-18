package concurrency.yield;

class Producer extends Thread{
  public void run(){
    for (int i = 0; i < 5; i++) {
      System.out.println("Producer: Produced Item - "+i);
      Thread.yield();
    }
  }
}
