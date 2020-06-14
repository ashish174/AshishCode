package javaparactice.concurrency.joinyield;

public class Threadjoin {


  public static void main(String[] args) {
    Thread t1 = new Thread(new MyRunnable(), "Thread1");
    Thread t2 = new Thread(new MyRunnable(), "Thread2");
    Thread t3 = new Thread(new MyRunnable(), "Thread3");

    try {
      t1.start();
      t1.join();
      t2.start();
      t2.join();
      t3.start();
      System.out.println(Thread.currentThread().getName().toUpperCase()+" Thread Done");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }



}
