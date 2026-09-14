package javaparactice.concurrency.a_practice.mar2026.executorframework;

import java.util.Objects;

public final class Task {
    private final int id;
    private final String imageUrl;

    public Task(int id, String imageUrl) {
        this.id = id;
        this.imageUrl = Objects.requireNonNull(imageUrl, "imageUrl cannot be null");
    }

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
