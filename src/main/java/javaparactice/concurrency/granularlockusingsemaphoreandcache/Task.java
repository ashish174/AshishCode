package javaparactice.concurrency.granularlockusingsemaphoreandcache;

import com.google.common.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;

public class Task implements Runnable{
    Logger logger = LoggerFactory.getLogger(Task.class);
    private Long organizationId;
    private Cache<Long, Semaphore> organizationSemaphoreCache;
    private Object lock;

    public Task(Long organizationId, Cache<Long, Semaphore> organizationSemaphoreCache) {
        this.organizationId = organizationId;
        this.organizationSemaphoreCache = organizationSemaphoreCache;
    }

    public Task(Long organizationId, Cache<Long, Semaphore> organizationSemaphoreCache, Object lock) {
        this.organizationId = organizationId;
        this.organizationSemaphoreCache = organizationSemaphoreCache;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (this.lock){
            Semaphore semaphore = organizationSemaphoreCache.getIfPresent(organizationId);
            if(semaphore==null){
                semaphore = new Semaphore(1);
                logger.info("Thread {} inserting into cache for Organization {}", Thread.currentThread().getName(), organizationId);
                organizationSemaphoreCache.put(organizationId, semaphore);
            }
        }
        Semaphore orgSemaphore = organizationSemaphoreCache.getIfPresent(organizationId);
        try {
            logger.info("Thread {} Trying to acquire Semaphore for Organization {}", Thread.currentThread().getName(), organizationId);
            orgSemaphore.acquire();
            logger.info("Thread {} Acquired Semaphore for Organization {}", Thread.currentThread().getName(), organizationId);
            logger.info("Thread {} sleeping for 10 ms", Thread.currentThread().getName());
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            logger.info("Thread {} Releasing Semaphore for Organization {}", Thread.currentThread().getName(), organizationId);
            orgSemaphore.release();
        }
    }
}
