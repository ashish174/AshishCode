package javaparactice;

import java.util.ArrayList;
import java.util.List;

public class Sample {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("juli");
        stringList.add("anshu");
        stringList.add("ashish");
        stringList.add("asakakumar");

        stringList.sort((o1, o2) -> o1.length() - o2.length());

        System.out.println(stringList);
    }
    void sample(){
        Thread thread = new Thread(() -> {
            System.out.println("Running thread "+Thread.currentThread());
        });
    }
}
