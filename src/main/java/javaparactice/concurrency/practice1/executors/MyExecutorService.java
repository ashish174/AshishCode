package javaparactice.concurrency.practice1.executors;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyExecutorService {
    private Queue<Task> taskQueue;
    private Thread[] threadPool;

    private static final int THREAD_POOL_DEFAULT_SIZE = 5;

    public MyExecutorService() {
        taskQueue = new LinkedBlockingQueue();
        threadPool = new Thread[THREAD_POOL_DEFAULT_SIZE];
        createAllWorkerThread();
    }

    public MyExecutorService(int poolSize) {
        taskQueue = new LinkedBlockingQueue();
        threadPool = new Thread[poolSize];
        createAllWorkerThread();
    }

    private void createAllWorkerThread() {
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i] = new WorkerThread(taskQueue);
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
        for (int i = 0; i < 20; i++) {
            Task task = new Task(i);
            executorService.taskQueue.add(task);
        }

    }


}
