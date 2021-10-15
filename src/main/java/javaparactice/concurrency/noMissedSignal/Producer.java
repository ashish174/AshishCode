package javaparactice.concurrency.noMissedSignal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Producer implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    AtomicBoolean producing;
    List<Integer> taskQueue;

    public Producer(AtomicBoolean toProduce, List<Integer> taskQueue) {
        this.producing = toProduce;
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (i < 10) {
                synchronized (producing) {
                    if(producing.get() == false) {
                        producing.wait();
                    }
                    //LOGGER.info("Producer Enqueing");
                    Random random = new Random();
                    int randInt = random.nextInt(10);
                    taskQueue.add(randInt);
                    LOGGER.info("{} enqueued : {}", Thread.currentThread().getName(), randInt);
                    producing.set(false);
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
