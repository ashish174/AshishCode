package javaparactice.concurrency.noMissedSignal;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) {
        AtomicBoolean producing = new AtomicBoolean(true);
        List<Integer> taskQueue = new LinkedList<>();
        Thread producer = new Thread(new Producer(producing, taskQueue));
        Thread consumer = new Thread(new Consumer(producing, taskQueue));
        producer.start();
        consumer.start();
    }
}
