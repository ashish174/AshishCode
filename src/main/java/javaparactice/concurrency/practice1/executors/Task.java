package javaparactice.concurrency.practice1.executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);

    int id;

    public Task(int id) {
        this.id = id;
    }

    public void printValue() {
        LOGGER.info("{} : {}", Thread.currentThread().getName(), id);
    }
}
