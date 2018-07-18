package concurrency.joinyield;

public class MyRunnable implements Runnable{

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + " Executing");
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName() + " Done");
  }
}
