package concurrency.usingLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task implements Runnable {
    Logger logger = LoggerFactory.getLogger(Task.class);

    @Override
    public void run() {
        logger.info("Thread {} running", Thread.currentThread().getName());
        try {
            logger.info("Thread {} sleeping for 5 ms", Thread.currentThread().getName());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
