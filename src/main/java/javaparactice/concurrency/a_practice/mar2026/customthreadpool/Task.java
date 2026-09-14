package javaparactice.concurrency.a_practice.mar2026.customthreadpool;

public final class Task {
    private final int taskId;
    private final int priority;

    public Task(int taskId, int priority) {
        this.taskId = taskId;
        this.priority = priority;
    }

    public int getTaskId() {
        return taskId;
    }

    public int getPriority() {
        return priority;
    }

    public void execute() {
        System.out.printf("Task Executing id=%d priority=%d thread=%s%n", taskId, priority,
                Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.printf("Task Interrupted id=%d thread=%s%n", taskId, Thread.currentThread().getName());
            return;
        }
        System.out.printf("Task Completed id=%d priority=%d thread=%s%n", taskId, priority,
                Thread.currentThread().getName());
    }
}
