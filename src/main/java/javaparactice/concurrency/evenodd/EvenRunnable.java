package javaparactice.concurrency.evenodd;

import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Primitive type Object like Boolean are immutable. Hence if you reassign it, Lock wont work.
 * Hence using Atomic Boolean
 */
public class EvenRunnable implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(EvenRunnable.class);
    AtomicBoolean isEven;

    public EvenRunnable(AtomicBoolean isEven) {
        this.isEven = isEven;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (isEven) {
                if (isEven.get()) {
                    LOGGER.info("Even Thread");
                    isEven.set(false);
                    isEven.notify();
                } else {
                    try {
                        isEven.wait();
                    } catch (Exception ex) {
                        LOGGER.error("{}", ex);
                    }
                }
            }

        }
    }
}
