package concurrency.executorFramework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<String> {
    public static Logger logger = LoggerFactory.getLogger(CallableTask.class);
    private int sequence;
    private String type;

    public CallableTask(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public String call() throws Exception {
        logger.info("Thread {} Started with Task {} of type {}.", Thread.currentThread().getName(), sequence, type);
        try {
            Thread.sleep(2000);
            type=sequence%2==0?"EVEN":"ODD";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Thread {} Completed with Task {} of type {}.", Thread.currentThread().getName(), sequence,type);
        return type;
    }
}
