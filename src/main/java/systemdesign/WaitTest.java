package systemdesign;

import java.util.HashSet;

public class WaitTest {
    private String s1;

    public WaitTest(String s1) {
        this.s1 = s1;
    }

    public static void main(String[] args) {
        HashSet<Object> hs = new HashSet<>();
        WaitTest t1 = new WaitTest("ab");
        WaitTest t2 = new WaitTest("ab");
        String s1 = "ab";
        String s2 = "ab";
        hs.add(t1);
        hs.add(t2);
        hs.add(s1);
        hs.add(s2);
        System.out.println(hs.size());
    }
}
