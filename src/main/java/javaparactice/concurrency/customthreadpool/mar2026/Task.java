package javaparactice.concurrency.customthreadpool.mar2026;

public class Task{
    public int taskId;
    public int priority;

    public Task(int taskId, int priority){
        this.taskId = taskId;
        this.priority = priority;
    }

    public void execute(){
        System.out.printf("Task Executing %d, Priority %d Thread : %s \n", taskId, priority, Thread.currentThread().getName());
        try{
            Thread.sleep(5000);
        } catch(InterruptedException e){
            throw new RuntimeException("Sleep interrupted", e);
        }
        System.out.printf("Task Completed %d, Priority %d Thread : %s \n", taskId, priority, Thread.currentThread().getName());
    }

}
