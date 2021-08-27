package javaparactice;

public class Sample {
    void sample(){
        Thread thread = new Thread(() -> {
            System.out.println("Running thread "+Thread.currentThread());
        });
    }
}
