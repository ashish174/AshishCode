package concurrency.threadCommunication;

public class OddThread implements Runnable{
  IsEven isEven;
  public OddThread(IsEven isEven) {
    this.isEven = isEven;
  }
  @Override
  public void run() {
    int num = 1;
    for(int i=0; i<20; i++){
      synchronized (isEven){
        try {
          if(isEven.getValue()){
            System.out.println(Thread.currentThread().getName()+ "waiting for Odd turn");
            isEven.wait();
          }
          System.out.println(Thread.currentThread().getName()+ " on Odd Turn");
          System.out.println(Thread.currentThread().getName()+" : " +num);
          num = num+2;
          isEven.setValue(true);
          Thread.sleep(1000);
          isEven.notify();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

    }
  }
}
