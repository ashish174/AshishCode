package javaparactice.concurrency.a_practice.mar2026.executorframework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Requirements:
 * 1) Fixed Throughput:
 *    Use an ExecutorService with a fixed pool of N threads (e.g., 10).
 * 2) The Task:
 *    Each task should:
 *    - Simulate fetching an image (use Thread.sleep for a random duration between 50-200ms).
 *    - "Process" the image (e.g., return URL length or a mock byte count).
 * 3) Timeout Handling:
 *    If a single image takes longer than 150ms to fetch, task should be cancelled/interrupted
 *    and a "Timeout" result should be recorded.
 * 4) Batch Result Aggregation:
 *    Main thread submits a batch of 50 URLs.
 *    Use Future or CompletableFuture to collect all results.
 * 5) Critical Failure Short-Circuit:
 *    If a task reports critical failure (URL contains "FAIL"), do not wait for all tasks.
 *    Cancel pending tasks and return immediately with aggregated results.
 */
public final class ThreadScheduler implements AutoCloseable {
    private static final long FETCH_TIMEOUT_MS = 150;

    private final ExecutorService workerPool;
    private final ScheduledExecutorService timeoutCanceller;

    public ThreadScheduler(int numThreads) {
        if (numThreads <= 0) {
            throw new IllegalArgumentException("numThreads must be > 0");
        }
        this.workerPool = Executors.newFixedThreadPool(numThreads);
        this.timeoutCanceller = Executors.newSingleThreadScheduledExecutor();
    }

    public BatchResult processBatch(List<String> urls) throws InterruptedException {
        ExecutorCompletionService<ImageResult> completionService =
                new ExecutorCompletionService<>(workerPool);
        List<Future<ImageResult>> allFutures = new ArrayList<>(urls.size());
        Map<Future<ImageResult>, String> urlByFuture = new IdentityHashMap<>();
        Map<Future<ImageResult>, ResultStatus> cancellationReason = new IdentityHashMap<>();
        Set<String> pendingUrls = new LinkedHashSet<>(urls);
        List<ImageResult> results = new ArrayList<>(urls.size());

        for (int i = 0; i < urls.size(); i++) {
            Task task = new Task(i, urls.get(i));
            Future<ImageResult> future = completionService.submit(new Worker(task));
            allFutures.add(future);
            urlByFuture.put(future, task.getImageUrl());

            timeoutCanceller.schedule(() -> {
                if (future.cancel(true)) {
                    synchronized (cancellationReason) {
                        cancellationReason.put(future, ResultStatus.TIMEOUT);
                    }
                }
            }, FETCH_TIMEOUT_MS, TimeUnit.MILLISECONDS);
        }

        int completed = 0;
        boolean criticalFailureTriggered = false;

        while (completed < allFutures.size()) {
            Future<ImageResult> finishedFuture = completionService.take();
            completed++;

            String url = urlByFuture.get(finishedFuture);
            pendingUrls.remove(url);

            try {
                ImageResult result = finishedFuture.get();
                results.add(result);

                if (result.status == ResultStatus.CRITICAL_FAILURE) {
                    criticalFailureTriggered = true;
                    break;
                }
            } catch (CancellationException e) {
                ResultStatus status;
                synchronized (cancellationReason) {
                    status = cancellationReason.getOrDefault(finishedFuture, ResultStatus.CANCELLED);
                }
                results.add(ImageResult.of(url, status, 0,
                        "Task cancelled before completion", System.currentTimeMillis()));
            } catch (ExecutionException e) {
                String message = e.getCause() == null ? "Task execution failed" : e.getCause().getMessage();
                results.add(ImageResult.of(url, ResultStatus.FAILED, 0, message, System.currentTimeMillis()));
            }
        }

        if (criticalFailureTriggered) {
            for (Future<ImageResult> future : allFutures) {
                if (!future.isDone() && future.cancel(true)) {
                    synchronized (cancellationReason) {
                        cancellationReason.put(future, ResultStatus.CANCELLED);
                    }
                }
            }

            for (String pendingUrl : pendingUrls) {
                results.add(ImageResult.of(pendingUrl, ResultStatus.CANCELLED, 0,
                        "Skipped due to critical failure short-circuit", System.currentTimeMillis()));
            }
        }

        return new BatchResult(results, criticalFailureTriggered);
    }

    @Override
    public void close() {
        workerPool.shutdownNow();
        timeoutCanceller.shutdownNow();
    }

    public enum ResultStatus {
        SUCCESS,
        TIMEOUT,
        FAILED,
        CANCELLED,
        CRITICAL_FAILURE
    }

    public static final class ImageResult {
        private final String url;
        private final ResultStatus status;
        private final int processedBytes;
        private final String message;
        private final long completedAtMs;

        private ImageResult(String url, ResultStatus status, int processedBytes, String message, long completedAtMs) {
            this.url = url;
            this.status = status;
            this.processedBytes = processedBytes;
            this.message = message;
            this.completedAtMs = completedAtMs;
        }

        public static ImageResult success(String url, long startedAt, int processedBytes) {
            long tookMs = System.currentTimeMillis() - startedAt;
            return new ImageResult(url, ResultStatus.SUCCESS, processedBytes,
                    "Fetch + process completed in " + tookMs + "ms", System.currentTimeMillis());
        }

        public static ImageResult criticalFailure(String url, long startedAt, String reason) {
            long tookMs = System.currentTimeMillis() - startedAt;
            return new ImageResult(url, ResultStatus.CRITICAL_FAILURE, 0,
                    reason + " after " + tookMs + "ms", System.currentTimeMillis());
        }

        public static ImageResult of(String url, ResultStatus status, int processedBytes,
                                     String message, long completedAtMs) {
            return new ImageResult(url, status, processedBytes, message, completedAtMs);
        }

        public String getUrl() {
            return url;
        }

        public ResultStatus getStatus() {
            return status;
        }

        public int getProcessedBytes() {
            return processedBytes;
        }

        public String getMessage() {
            return message;
        }

        public long getCompletedAtMs() {
            return completedAtMs;
        }

        @Override
        public String toString() {
            return "ImageResult{" +
                    "url='" + url + '\'' +
                    ", status=" + status +
                    ", processedBytes=" + processedBytes +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    public static final class BatchResult {
        private final List<ImageResult> results;
        private final boolean criticalFailureTriggered;

        public BatchResult(List<ImageResult> results, boolean criticalFailureTriggered) {
            this.results = List.copyOf(results);
            this.criticalFailureTriggered = criticalFailureTriggered;
        }

        public List<ImageResult> getResults() {
            return Collections.unmodifiableList(results);
        }

        public boolean isCriticalFailureTriggered() {
            return criticalFailureTriggered;
        }
    }
}
