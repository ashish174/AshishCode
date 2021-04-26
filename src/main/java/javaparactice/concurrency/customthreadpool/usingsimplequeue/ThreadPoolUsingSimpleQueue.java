package javaparactice.concurrency.customthreadpool.usingsimplequeue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import javaparactice.concurrency.customthreadpool.Task;

public class ThreadPoolUsingSimpleQueue {
  private  int numOfThreads;
  private Thread[] threads;
  private Queue<Runnable> queue;

  public ThreadPoolUsingSimpleQueue(int numOfThreads) {
    this.numOfThreads = numOfThreads;
    threads = new PoolWorkerUsingSimpleQueue[numOfThreads];
    queue = new LinkedList<>();
    for(int i = 0; i < numOfThreads; i++){
      threads[i] = new PoolWorkerUsingSimpleQueue(queue);
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
    ThreadPoolUsingSimpleQueue myThreadPool = new ThreadPoolUsingSimpleQueue(7);
    for(int i=0; i<3;i++){
      //int repeatnum = (int) (Math.random()*10);
      int repeatnum = new Random().nextInt(10);
      Task task = new Task(repeatnum);
      myThreadPool.enqueueTask(task);
    }
  }

}
