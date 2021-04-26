package javaparactice.concurrency.customthreadpool;

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
      threads[i] = new PoolWorker(queue);
      threads[i].start();
    }
  }

  public void enqueueTask(Runnable task){
    synchronized (queue){
      queue.add(task);
      queue.notify();
    }
  }

  public static void main(String[] args) {
    ThreadPool myThreadPool = new ThreadPool(7);
    for(int i=0; i<3;i++){
      //int repeatnum = (int) (Math.random()*10);
      int repeatnum = new Random().nextInt(10);
      Task task = new Task(repeatnum);
      myThreadPool.enqueueTask(task);
    }
  }

}
