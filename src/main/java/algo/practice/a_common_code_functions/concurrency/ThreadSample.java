package algo.practice.a_common_code_functions.concurrency;

public class ThreadSample {
    public static void main(String[] args){
        Thread client1 = new Thread(() -> {
            for(int i=0; i < 50; i++){
                    System.out.println("Hello "+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }, "Thread1");

        client1.start();


        Runnable runnable = () -> {
            for(int i=0; i < 50; i++){
                System.out.println("Hello "+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        };

        Thread client2 = new Thread(runnable, "Thread2");
        Thread client3 = new Thread(runnable, "Thread3");

        client2.start();
        client3.start();
    }
}
