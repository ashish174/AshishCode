package javaparactice.concurrency.a_practice.mar2026.customthreadpool;

import java.util.concurrent.TimeUnit;

public final class Worker implements Runnable {
    private final ThreadScheduler scheduler;

    public Worker(ThreadScheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }

            if (scheduler.shouldTerminateWorker()) {
                return;
            }

            ThreadScheduler.PrioritizedTask prioritizedTask;
            try {
                prioritizedTask = scheduler.pollTask(250, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            if (prioritizedTask == null) {
                continue;
            }

            try {
                prioritizedTask.task().execute();
            } catch (Throwable t) {
                System.err.printf("Task Failed id=%d error=%s%n", prioritizedTask.task().getTaskId(), t.getMessage());
            }
        }
    }
}
