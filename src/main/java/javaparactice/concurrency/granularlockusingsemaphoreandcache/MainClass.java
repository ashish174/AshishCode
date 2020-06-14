package javaparactice.concurrency.granularlockusingsemaphoreandcache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class MainClass {
    public static void main(String[] args) {
        Object lock = new Object();
         Cache<Long, Semaphore> organizationSemaphoreCache = CacheBuilder.newBuilder()
            .expireAfterAccess(1, TimeUnit.MINUTES)
            .maximumSize(10)
            .build();
         Task task1 = new Task(123L, organizationSemaphoreCache, lock);
         Task task2 = new Task(124L, organizationSemaphoreCache, lock);
         Task task3 = new Task(124L, organizationSemaphoreCache, lock);
         Task task4 = new Task(124L, organizationSemaphoreCache, lock);
         Task task5 = new Task(124L, organizationSemaphoreCache, lock);
         Task task6 = new Task(124L, organizationSemaphoreCache, lock);
         Task task7 = new Task(124L, organizationSemaphoreCache, lock);
         Thread t1 = new Thread(task1);
         Thread t2 = new Thread(task2);
         Thread t3 = new Thread(task3);
         Thread t4 = new Thread(task4);
         Thread t5 = new Thread(task5);
         Thread t6 = new Thread(task6);
         Thread t7 = new Thread(task7);
         t1.start();
         t2.start();
         t3.start();
         t4.start();
         t5.start();
         t6.start();
         t7.start();

    }
}
