package javaparactice.concurrency.practice1.serialnotifier;

import java.util.concurrent.LinkedBlockingQueue;

public class MyExecutorService {
    private LinkedBlockingQueue<Task> taskQueue;
    private LinkedBlockingQueue<Task> completedTaskQueue;
    private Thread[] threadPool;

    private static final int THREAD_POOL_DEFAULT_SIZE = 5;

    public MyExecutorService() {
        taskQueue = new LinkedBlockingQueue();
        completedTaskQueue = new LinkedBlockingQueue<>();
        threadPool = new Thread[THREAD_POOL_DEFAULT_SIZE];
        createAllWorkerThread();
    }

    public MyExecutorService(int poolSize) {
        taskQueue = new LinkedBlockingQueue();
        completedTaskQueue = new LinkedBlockingQueue<>();
        threadPool = new Thread[poolSize];
        createAllWorkerThread();
    }

    private void createAllWorkerThread() {
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i] = new WorkerThread(taskQueue, completedTaskQueue);
            threadPool[i].setName("WorkerThread-" + i);
        }
    }

    private void startAllThread() {
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i].start();
        }
    }

    public static void main(String[] args) {
        MyExecutorService executorService = new MyExecutorService(3);
        executorService.startAllThread();
        Thread completedTaskThread = new CompletedTaskThread(executorService.completedTaskQueue);
        completedTaskThread.start();
        for (int i = 0; i < 20; i++) {
            Task task = new Task(i);
            executorService.taskQueue.add(task);
        }

    }


}
