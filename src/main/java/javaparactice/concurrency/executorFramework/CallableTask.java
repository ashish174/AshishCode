package javaparactice.concurrency.executorFramework;

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

    /**
     * If you catch InterruptedException:
     * Then Always restore the interrupt unless you have a very specific reason not to.
     * Else shutdownNow() won't work for you as interrupt flag is cleared. And thread will keep running despite interrupt signal.
     * This cause memory leak as Executor cannot stop it properly.
     * Thread.currentThread().interrupt();
     */
    @Override
    public String call() throws Exception {
        logger.info("Thread {} Started with Task {} of type {}.", Thread.currentThread().getName(), sequence, type);
        try {
            Thread.sleep(2000);
            type=sequence%2==0?"EVEN":"ODD";
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // restore interrupt
            logger.warn("Thread {} interrupted. Stopping task {}.",
                    Thread.currentThread().getName(), sequence);
            return null; // stop execution
        }
        logger.info("Thread {} Completed with Task {} of type {}.", Thread.currentThread().getName(), sequence,type);
        return type;
    }
}
