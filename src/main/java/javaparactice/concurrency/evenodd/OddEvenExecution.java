package javaparactice.concurrency.evenodd;


import java.util.concurrent.atomic.AtomicBoolean;

public class OddEvenExecution {

    //Primitve type Object are immutable, so we cant use it for lock, as on reassignment the Object got changed
    //there we need to use some wrapper object with boolean variable inside.
    //private static volatile Boolean isEven = new Boolean(false);
    private static AtomicBoolean isEven = new AtomicBoolean(false);

    public static void main(String[] args) {
        Thread evenThread = new Thread(new EvenRunnable(isEven));
        Thread oddThread = new Thread(new OddRunnable(isEven));
        evenThread.start();
        oddThread.start();
    }
}
