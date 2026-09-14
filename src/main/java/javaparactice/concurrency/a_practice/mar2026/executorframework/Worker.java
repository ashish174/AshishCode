package javaparactice.concurrency.a_practice.mar2026.executorframework;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public final class Worker implements Callable<ThreadScheduler.ImageResult> {
    private static final int MIN_FETCH_MS = 50;
    private static final int MAX_FETCH_MS = 200;

    private final Task task;

    public Worker(Task task) {
        this.task = task;
    }

    @Override
    public ThreadScheduler.ImageResult call() throws Exception {
        long startedAt = System.currentTimeMillis();
        String url = task.getImageUrl();

        if (url.contains("FAIL")) {
            return ThreadScheduler.ImageResult.criticalFailure(url, startedAt,
                    "Critical failure marker found in URL");
        }

        int fetchLatencyMs = ThreadLocalRandom.current().nextInt(MIN_FETCH_MS, MAX_FETCH_MS + 1);
        Thread.sleep(fetchLatencyMs);

        int mockBytes = url.length() * 128;
        return ThreadScheduler.ImageResult.success(url, startedAt, mockBytes);
    }
}
