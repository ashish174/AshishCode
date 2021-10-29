package javaparactice.concurrency.cooperativeProducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Producer implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);
    Queue<Integer> taskQueue;
    AtomicBoolean isUsed;

    public Producer(Queue<Integer> taskQueue, AtomicBoolean atomicBoolean) {
        this.taskQueue = taskQueue;
        this.isUsed = atomicBoolean;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 10) {
            try {
                synchronized (isUsed) {
                    if (isUsed.get()) {
                        LOGGER.info("{} Waiting for isUsed to get false", Thread.currentThread().getName());
                        isUsed.wait();
                        LOGGER.info("{} received signal for isUsed with value false", Thread.currentThread().getName());
                    }

                    isUsed.set(true);
                    Random random = new Random();
                    int randInt = random.nextInt(10);
                    LOGGER.info(Thread.currentThread().getName() + ": Key : " + randInt);
                    Thread.sleep(2000);
                    taskQueue.add(randInt);
                    isUsed.set(false);
                    isUsed.notifyAll();
                }
            } catch (Exception ex) {
                LOGGER.error("Error : ", ex);
            }

            i++;
        }

    }

    public static void main(String[] args) {
        AtomicBoolean isUsed = new AtomicBoolean(false);
        Queue<Integer> taskQueue = new LinkedList<>();
        Thread firstProducer = new Thread(new Producer(taskQueue, isUsed));
        Thread secondProducer = new Thread(new Producer(taskQueue, isUsed));
        firstProducer.setName("FirstThread");
        secondProducer.setName("SecondThread");

        firstProducer.start();
        secondProducer.start();
        LOGGER.info("Main Thread finishes");

    }
}
