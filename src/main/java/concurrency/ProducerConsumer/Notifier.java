package concurrency.ProducerConsumer;

public class Notifier implements Runnable{
  private Message msg;

  public Notifier(Message msg) {
    this.msg = msg;
  }


  @Override
  public void run() {
    String name = Thread.currentThread().getName();
    System.out.println(name+ "Started");
    try {
      Thread.sleep(2000);
      synchronized (msg){
        msg.setWasNotified(true);
        msg.setMsg(name+" Notifier work done");
        //msg.notify();
        msg.notifyAll();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
