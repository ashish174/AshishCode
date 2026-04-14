package javaparactice.concurrency.a_practice.mar2026.boundblockingqueue.usingwaitnotify;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Task {
    // Immutable task id makes the sample task thread-safe by construction.
    private final int taskId;

    public Task(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void execute() {
        // Simulate task execution by logging the worker thread name.
        log.info("Executed Task with id: {}, thread: {}", taskId, Thread.currentThread().getName());
    }
}
