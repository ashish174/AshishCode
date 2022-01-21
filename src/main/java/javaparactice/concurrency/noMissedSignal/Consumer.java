package javaparactice.concurrency.noMissedSignal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Consumer implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);
    AtomicBoolean producing;
    List<Integer> taskQueue;

    public Consumer(AtomicBoolean producing, List<Integer> taskQueue) {
        this.producing = producing;
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (i < 10) {
                synchronized (producing) {
                    if (producing.get() == true) {
                        producing.wait();
                    }
                    //LOGGER.info("Consumer Dequeing");
                    Integer randInt = taskQueue.remove(0);
                    LOGGER.info("{} dequeued : {}", Thread.currentThread().getName(), randInt);
                    producing.set(true);
                    producing.notify();
                }
                i++;
                Thread.sleep(new Random().nextInt(4)*1000);
            }
        } catch (Exception ex) {
            LOGGER.error("Error : ", ex);
        }


    }
}
