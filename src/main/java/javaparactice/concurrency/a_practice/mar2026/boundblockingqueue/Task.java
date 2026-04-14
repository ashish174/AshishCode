package javaparactice.concurrency.a_practice.mar2026.boundblockingqueue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Task {
    int taskId;

    public Task(int taskId) {
        this.taskId = taskId;
    }
    public void execute(){
        log.info("Executed Task with id: {}, thread: {} ", taskId, Thread.currentThread().getName());
    }
}
