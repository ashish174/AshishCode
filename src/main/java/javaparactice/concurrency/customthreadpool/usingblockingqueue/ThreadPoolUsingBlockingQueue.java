package javaparactice.concurrency.customthreadpool.usingblockingqueue;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import javaparactice.concurrency.customthreadpool.Task;
import javaparactice.concurrency.customthreadpool.usingsimplequeue.PoolWorkerUsingSimpleQueue;

public class ThreadPoolUsingBlockingQueue {
  private  int numOfThreads;
  private Thread[] threads;
  private LinkedBlockingQueue queue;

  public ThreadPoolUsingBlockingQueue(int numOfThreads) {
    this.numOfThreads = numOfThreads;
    threads = new PoolWorkerUsingSimpleQueue[numOfThreads];
    queue = new LinkedBlockingQueue();
    for(int i = 0; i < numOfThreads; i++){
      threads[i] = new PoolWorkerUsingBlockingQueue(queue);
      threads[i].start();
    }
  }

  public void enqueueTask(Runnable task) throws InterruptedException {
      queue.put(task);
  }

  public static void main(String[] args) throws InterruptedException {
    ThreadPoolUsingBlockingQueue myThreadPool = new ThreadPoolUsingBlockingQueue(7);
    for(int i=0; i<3;i++){
      //int repeatnum = (int) (Math.random()*10);
      int repeatnum = new Random().nextInt(10);
      Task task = new Task(repeatnum);
      myThreadPool.enqueueTask(task);
    }
  }

}
