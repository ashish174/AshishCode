package javaparactice.concurrency.executorFramework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunnableTask implements Runnable {
    public static Logger logger = LoggerFactory.getLogger(RunnableTask.class);
    private int sequence;

    public RunnableTask(int sequence) {
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
  public void run() {
        logger.info("Thread {} Started with Task {}.", Thread.currentThread().getName(), sequence);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // restore interrupt
            logger.warn("Thread {} interrupted. Stopping task {}.",
                    Thread.currentThread().getName(), sequence);
            return; // stop execution
        }
        logger.info("Thread {} Completed with Task {}.", Thread.currentThread().getName(), sequence);

    }
}
