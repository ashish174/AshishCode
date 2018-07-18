package concurrency.ProducerConsumer;

public class Waiter implements Runnable {
  private Message msg;

  public Waiter(Message msg) {
    this.msg = msg;
  }

  @Override
  public void run() {
    String name = Thread.currentThread().getName();
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    synchronized (msg){
      if(!msg.isWasNotified()){
        try {
          System.out.println(name+" Waiting to get Notified at "+System.currentTimeMillis());
          msg.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      System.out.println(name+" got notified at "+System.currentTimeMillis());
      System.out.println(name+ " processed Message:- "+msg.getMsg());
      msg.setWasNotified(false);
    }

  }
}
