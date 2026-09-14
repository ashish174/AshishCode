package javaparactice.concurrency.a_practice.mar2026.executorframework;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            if (i == 17) {
                urls.add("https://cdn.example.com/FAIL/image-" + i + ".jpg");
            } else {
                urls.add("https://cdn.example.com/image-" + i + ".jpg");
            }
        }

        try (ThreadScheduler scheduler = new ThreadScheduler(10)) {
            ThreadScheduler.BatchResult batchResult = scheduler.processBatch(urls);

            int success = 0;
            int timeout = 0;
            int cancelled = 0;
            int failed = 0;
            int critical = 0;

            for (ThreadScheduler.ImageResult result : batchResult.getResults()) {
                switch (result.getStatus()) {
                    case SUCCESS -> success++;
                    case TIMEOUT -> timeout++;
                    case CANCELLED -> cancelled++;
                    case FAILED -> failed++;
                    case CRITICAL_FAILURE -> critical++;
                }
            }

            System.out.println("Critical failure triggered: " + batchResult.isCriticalFailureTriggered());
            System.out.println("SUCCESS=" + success +
                    " TIMEOUT=" + timeout +
                    " CANCELLED=" + cancelled +
                    " FAILED=" + failed +
                    " CRITICAL_FAILURE=" + critical);
        }
    }
}
