package javaparactice.concurrency.a_practice.mar2026.customthreadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public final class ThreadScheduler {
    private final int poolSize;
    private final PriorityBlockingQueue<PrioritizedTask> taskQueue;
    private final List<Thread> workerThreads;
    private final AtomicBoolean started;
    private final AtomicBoolean shutdown;
    private final AtomicBoolean stopNow;
    private final AtomicLong sequence;

    public ThreadScheduler(int poolSize) {
        if (poolSize <= 0) {
            throw new IllegalArgumentException("poolSize must be > 0");
        }
        this.poolSize = poolSize;
        this.taskQueue = new PriorityBlockingQueue<>();
        this.workerThreads = new ArrayList<>(poolSize);
        this.started = new AtomicBoolean(false);
        this.shutdown = new AtomicBoolean(false);
        this.stopNow = new AtomicBoolean(false);
        this.sequence = new AtomicLong(0);
    }

    public void start() {
        if (!started.compareAndSet(false, true)) {
            return;
        }

        for (int i = 0; i < poolSize; i++) {
            Thread thread = new Thread(new Worker(this), "custom-pool-worker-" + i);
            workerThreads.add(thread);
            thread.start();
        }
    }

    public void submit(Task task) {
        Objects.requireNonNull(task, "task cannot be null");
        if (!started.get()) {
            throw new IllegalStateException("Scheduler is not started");
        }
        if (shutdown.get()) {
            throw new IllegalStateException("Scheduler is shut down. Cannot accept new tasks");
        }

        taskQueue.offer(new PrioritizedTask(task, sequence.getAndIncrement()));
    }

    public void shutdown() {
        shutdown.set(true);
    }

    public List<Task> shutdownNow() {
        shutdown.set(true);
        stopNow.set(true);

        for (Thread workerThread : workerThreads) {
            workerThread.interrupt();
        }

        List<Task> notStartedTasks = new ArrayList<>();
        PrioritizedTask prioritizedTask;
        while ((prioritizedTask = taskQueue.poll()) != null) {
            notStartedTasks.add(prioritizedTask.task());
        }
        return notStartedTasks;
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        long timeoutMillis = unit.toMillis(timeout);
        long deadline = System.currentTimeMillis() + timeoutMillis;

        for (Thread workerThread : workerThreads) {
            long remaining = deadline - System.currentTimeMillis();
            if (remaining <= 0) {
                return false;
            }
            workerThread.join(remaining);
        }

        return true;
    }

    boolean shouldTerminateWorker() {
        if (stopNow.get()) {
            return true;
        }
        return shutdown.get() && taskQueue.isEmpty();
    }

    PrioritizedTask pollTask(long timeout, TimeUnit unit) throws InterruptedException {
        return taskQueue.poll(timeout, unit);
    }

    static final class PrioritizedTask implements Comparable<PrioritizedTask> {
        private final Task task;
        private final long sequence;

        PrioritizedTask(Task task, long sequence) {
            this.task = task;
            this.sequence = sequence;
        }

        Task task() {
            return task;
        }

        @Override
        public int compareTo(PrioritizedTask other) {
            int priorityComparison = Integer.compare(other.task.getPriority(), task.getPriority());
            if (priorityComparison != 0) {
                return priorityComparison;
            }
            return Long.compare(sequence, other.sequence);
        }
    }
}
