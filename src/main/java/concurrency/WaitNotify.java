package concurrency;

import com.sun.corba.se.spi.monitoring.MonitoredObject;

public class WaitNotify {
  Object monitorObject = new Object();

  public void dowait(){
    synchronized (monitorObject){
      try {
        monitorObject.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void donotify(){
    synchronized (monitorObject){
      //do some operations
      monitorObject.notify();
    }
  }

  public void doJobWithMonitorOnject(){
    System.out.println(Thread.currentThread().getName()+" Inside Job WithMonitorOnject");
  }

  public static void main(String[] args) {
    Thread T1 = new Thread(new MyRunnable(), "AshishThread");
    Thread T2  =new Thread(new MyRunnable(), "AbhishekThread");
    T1.start();
    T2.start();
  }
}

class MyRunnable implements Runnable{
  Object monitorObject = new Object();
  boolean wasSignalled = false;


  public void dowait(){
    synchronized (monitorObject){
      if(!wasSignalled){
      try {
        monitorObject.wait(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      }
      wasSignalled = false;
    }
  }

  public void donotify(){
    synchronized (monitorObject){
      //do some operations
      wasSignalled=true;
      monitorObject.notify();
    }
  }

  public void doJobWithMonitorOnject(){
    System.out.println(Thread.currentThread().getName()+" Inside Job WithMonitorOnject");
  }

  @Override
  public void run() {
    for(int i=0; i < 50 ; i++){
      System.out.println(Thread.currentThread()+" "+i);
      try {
        dowait();
        System.out.println(Thread.currentThread().getName()+" Inside Job WithMonitorOnject");
        Thread.sleep(2000);
        donotify();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

