package algo.practice.a_interviewpractice.apr2026.blockingqueue;

import javaparactice.concurrency.a_practice.mar2026.boundblockingqueue.usingReentrantLock.Task;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomBlockingQueue<T> {
    private final Deque<T> dequeue;
    private final int capacity;
    private final Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    private boolean closed;

    public CustomBlockingQueue(int capacity){
        dequeue = new LinkedList<>();
        this.capacity = capacity;
        closed = false;
    }

    public void add(T task) throws InterruptedException {
        lock.lock();
        try{
            while(dequeue.size() >= capacity && !closed){
                System.out.printf("Add: Queue is full. Wait for removal.\n");
                notFull.await();
            }
            if(closed){
                throw new IllegalStateException("Queue is closed for adding");
            }

            dequeue.addLast(task);
            System.out.printf("Add: Item added.\n");
            notEmpty.signal();
        } finally{
            lock.unlock();
        }

    }

    public T remove() throws InterruptedException {
        lock.lock();
        try{
            while(dequeue.size()==0 && !closed){
                System.out.printf("Remove: Queue is empty. Wait for addition.\n");
                notEmpty.await();
            }
            //drain the queue
            if(closed && dequeue.isEmpty()){
                // closed and nothing to consume
                return null;
            }
            T task = dequeue.removeFirst();
            System.out.printf("Remove: Item removed.\n");
            notFull.signal();
            return task;
        } finally{
            lock.unlock();
        }
    }

    public void close(){
        lock.lock();
        try{
            this.closed = true;
            notFull.signalAll();
            notEmpty.signalAll();
        }finally{
            lock.unlock();
        }
    }

    public boolean isClosed(){
        lock.lock();
        try{
            return this.closed;
        }finally{
            lock.unlock();
        }
    }


    public static class Task{
        String name;

        public Task(String name){
            this.name = name;
        }
        public void execute() throws InterruptedException {
            System.out.printf("Executing Task %s \n", name);
            Thread.sleep(1000*(long)(Math.random()*3));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CustomBlockingQueue<Task> customBlockingQueue = new CustomBlockingQueue(3);
        Producer producer1 = new Producer(customBlockingQueue, "producer1");
        Producer producer2 = new Producer(customBlockingQueue, "producer2");
        Consumer consumer1 = new Consumer(customBlockingQueue, "consumer1");
        Consumer consumer2 = new Consumer(customBlockingQueue, "consumer2");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
        Thread.sleep(5000);
        customBlockingQueue.close();
    }

}
