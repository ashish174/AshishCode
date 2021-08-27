package javaparactice.concurrency.customthreadpool;

public class Task implements Runnable{
  private int num;

  public Task(int num) {
    this.num = num;
  }

  @Override
  public void run() {
    for(int i=0; i < num; i++)
    System.out.println(Thread.currentThread().getName()+" : Task with Repeat times "+num+" is Running for "+i);
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
