package javaparactice.concurrency.practice1.serialnotifier;

import java.util.Random;
import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task implements Callable<Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);

    int id;

    public Task(int id) {
        this.id = id;
    }

    public void printValue() {
        LOGGER.info("{} : {}", Thread.currentThread().getName(), id);
    }

    @Override
    public Integer call() throws Exception {
        long sleepTime = 1000*new Random().nextInt(10);
        LOGGER.info("{} : id {} sleeping for {}", Thread.currentThread().getName(), id, sleepTime);
        Thread.sleep(1000*id);
        return id;
    }
}
