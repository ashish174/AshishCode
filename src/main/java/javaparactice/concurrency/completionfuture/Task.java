package javaparactice.concurrency.completionfuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);
    int durationToExecute;

    public Task(int durationToExecute) {
        this.durationToExecute = durationToExecute;
    }

    public int execute() {
        try {
            LOGGER.info("[{}] Task will execute for {} s", Thread.currentThread().getName(), durationToExecute);
            Thread.sleep(durationToExecute * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("[{}] Task Completed execution", Thread.currentThread().getName(), durationToExecute);
        return durationToExecute;
    }
}
