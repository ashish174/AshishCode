package javaparactice.concurrency.a_practice.mar2026.executorframework.V2;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/**
 * Rank #2: Design a Custom Task Scheduler
 * The Problem
 * Implement a Distributed-style Task Scheduler on a single machine. It should allow multiple producer threads to submit tasks to be executed at a specific time in the future.
 *
 * Requirements:
 *
 * schedule(Runnable task, long delayMs): Schedules a task to run after a delay.
 *
 * Concurrency: Multiple threads must be able to schedule tasks simultaneously.
 *
 * Execution: Tasks must execute as close to their scheduled time as possible.
 *
 * Resource Management: You cannot spawn a new thread for every single task. You must use a fixed pool of "worker" threads.
 *
 * **/
public class MyTaskScheduler {

    /**
     * Internal wrapper for the task and its scheduled execution time.
     */
    private static class ScheduledTask implements Comparable<ScheduledTask> {
        final Runnable runnable;
        final long executeAt;

        ScheduledTask(Runnable runnable, long delayMs) {
            this.runnable = runnable;
            this.executeAt = System.currentTimeMillis() + delayMs;
        }

        @Override
        public int compareTo(ScheduledTask other) {
            return Long.compare(this.executeAt, other.executeAt);
        }
    }

    // PriorityQueue to always get the nearest task first
    private final PriorityQueue<ScheduledTask> taskQueue = new PriorityQueue<>();
    private final Lock lock = new ReentrantLock();
    private final Condition newTaskAdded = lock.newCondition();

    // Fixed worker pool to execute the tasks
    private final ExecutorService workerPool;
    private boolean isRunning = true;

    public MyTaskScheduler(int workerCount) {
        this.workerPool = Executors.newFixedThreadPool(workerCount);
        startManagerThread();
    }

    /**
     * Producers call this to schedule work.
     */
    public void schedule(Runnable task, long delayMs) {
        lock.lock();
        try {
            ScheduledTask newTask = new ScheduledTask(task, delayMs);
            taskQueue.add(newTask);
            // If this task is now the earliest, wake up the manager thread
            newTaskAdded.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * The Manager Thread: Responsible for watching the queue and
     * dispatching tasks to the worker pool at the right time.
     */
    private void startManagerThread() {
        Thread manager = new Thread(() -> {
            while (isRunning) {
                lock.lock();
                try {
                    while (taskQueue.isEmpty()) {
                        newTaskAdded.await(); // Wait until a task is added
                    }

                    long now = System.currentTimeMillis();
                    ScheduledTask nearestTask = taskQueue.peek();

                    if (now >= nearestTask.executeAt) {
                        // Time to run!
                        taskQueue.poll();
                        workerPool.submit(nearestTask.runnable);
                    } else {
                        // Wait until the time for the nearest task arrives
                        long sleepTime = nearestTask.executeAt - now;
                        newTaskAdded.await(sleepTime, TimeUnit.MILLISECONDS);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } finally {
                    lock.unlock();
                }
            }
        });
        manager.setDaemon(true);
        manager.start();
    }

    public void stop() {
        isRunning = false;
        workerPool.shutdown();
    }
}