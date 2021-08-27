package javaparactice.concurrency.joinyield;

public class Threadjoin {


  public static void main(String[] args) {
    Thread t1 = new Thread(new MyRunnable(), "Thread1");
    Thread t2 = new Thread(new MyRunnable(), "Thread2");
    Thread t3 = new Thread(new MyRunnable(), "Thread3");

    try {
      t1.start();
      // This will join t1 to current Main Thread, i.e. Main Thread will now wait till t1 finishes
      t1.join();
      t2.start();
      // This will join t2 to current Main Thread, i.e. Main Thread will now wait till t2 finishes
      t2.join();
      t3.start();
      System.out.println(Thread.currentThread().getName().toUpperCase()+" Thread Done");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }



}
