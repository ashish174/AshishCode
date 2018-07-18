package concurrency.customthreadpool;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
  private  int numOfThreads;
  private Thread[] threads;
  private LinkedBlockingQueue queue;

  public ThreadPool(int numOfThreads) {
    this.numOfThreads = numOfThreads;
    threads = new PoolWorker[numOfThreads];
    queue = new LinkedBlockingQueue();
    for(int i = 0; i < numOfThreads; i++){
      threads[i] = new PoolWorker();
      threads[i].start();
    }
  }

  public void execute(Runnable task){
    synchronized (queue){
      queue.add(task);
      queue.notify();
    }
  }

  public class PoolWorker extends Thread{
    @Override
    public void run() {
      Runnable task;
      while(true){
        synchronized (queue){
          if(queue.isEmpty()){
            try {
              queue.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          task = (Task)queue.poll();
        }
        try {
          task.run();
        }catch (RuntimeException e) {
          System.out.println("Thread pool is interrupted due to an issue: " + e.getMessage());
        }

      }
    }
  }

  public static void main(String[] args) {
    ThreadPool myThreadPool = new ThreadPool(7);
    for(int i=0; i<3;i++){
      //int repeatnum = (int) (Math.random()*10);
      int repeatnum = new Random().nextInt(10);
      Task task = new Task(repeatnum);
      myThreadPool.execute(task);
    }
  }

}
