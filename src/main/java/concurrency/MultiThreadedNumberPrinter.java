package concurrency;

public class MultiThreadedNumberPrinter {
  private volatile Boolean isEven = false;

  class EvenPrinter implements Runnable{

    @Override
    public void run() {
      for(int i=0; i< 10 ; i++){
      System.out.println("EvenThread"+Thread.currentThread().getName()+" = "+i);
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  class OddPrinter implements Runnable{

    @Override
    public void run() {
      for(int i=0; i< 10 ; i++) {
        System.out.println("OddThread " + Thread.currentThread().getName()+" = "+i);
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public  void doThreadScheduling(){
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
