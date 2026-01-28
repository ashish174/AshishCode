package javaparactice.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
/**
 * Boolean objects in Java are immutable. That means if you try to “toggle” it, you’re really just
 * creating a new object and reassigning the reference. In multithreaded code, this is unsafe unless
 * you guard access with synchronization. Hence it is good to use AtomicBoolean.
 */
public class MultiThreadedNumberPrinter {
  private AtomicBoolean isEven = new AtomicBoolean(false);

  class EvenPrinter implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        synchronized (isEven) {
          try {
            isEven.wait();
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          if (isEven.get()) {
            log.info("Even Thread {}: {}", Thread.currentThread().getName(), i);
            isEven.set(false);
          }
          isEven.notify();
        }
      }
    }
  }

  class OddPrinter implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        synchronized (isEven) {
          try {
            isEven.wait();
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          if (!isEven.get()) {
            log.info("Odd Thread {}: {}", Thread.currentThread().getName(), i);
            isEven.set(true);
          }
          isEven.notify();
        }
      }
    }
  }

  public void doThreadScheduling() {
    Thread T1 = new Thread(new EvenPrinter());
    Thread T2 = new Thread(new OddPrinter());
    T1.start();
    T2.start();
  }

  public static void main(String[] args) {
    MultiThreadedNumberPrinter m = new MultiThreadedNumberPrinter();
    m.doThreadScheduling();
  }
}
