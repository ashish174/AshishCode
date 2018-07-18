package concurrency.ProducerConsumer;

public class WaitNotifyTest {

  public static void main(String[] args) {
    Message message = new Message("process it");
    Waiter waiter1 = new Waiter(message);
    new Thread(waiter1, "Waiter1").start();

    Waiter waiter2 = new Waiter(message);
    new Thread(waiter2, "Waiter2").start();

    Notifier notifier = new Notifier(message);
    new Thread(notifier, "Notifier").start();
    System.out.println("All threads are started");
  }
}
