package javaparactice.concurrency.evenodd;

import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OddRunnable implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(OddRunnable.class);
    AtomicBoolean isEven;

    public OddRunnable(AtomicBoolean isEven) {
        this.isEven = isEven;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (isEven) {
                if (!isEven.get()) {
                    LOGGER.info("Odd Thread");
                    isEven.set(true);
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
