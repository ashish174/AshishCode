package javaparactice.concurrency.threadCommunication;

public class EvenThread implements Runnable{
  IsEven isEven;
  public EvenThread(IsEven isEven) {
    this.isEven = isEven;
  }

  @Override

  public void run() {
    int num = 0;
    for(int i=0; i<20; i++){
      synchronized (isEven){
        try {
          if(!isEven.getValue()){
            System.out.println(Thread.currentThread().getName()+ " waiting for Even turn");
            isEven.wait();
          }
          System.out.println(Thread.currentThread().getName()+ " on Even Turn");
          System.out.println(Thread.currentThread().getName()+" : " +num);
          num = num+2;
          isEven.setValue(false);
          Thread.sleep(1000);
          isEven.notify();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

      }
    }
  }
}
