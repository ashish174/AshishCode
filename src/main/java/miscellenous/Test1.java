package miscellenous;

import com.google.common.collect.Sets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Test1 {
    public static void main(String[] args) throws ParseException {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        List<Integer> d = new ArrayList<>();

        //d.add(3);

        a.add(1);
        a.add(2);

        b.add(3);
        b.add(4);

        c.addAll(a);
        c.addAll(b);
        System.out.println(c);

        Map<Integer, String> bundlePrefByIds = d.stream().collect(Collectors.toMap(p -> p, p -> "hiii"));
        Set<Integer> set1 = new HashSet<>();
        set1.add(2);
        set1.add(1);


        Set<Integer> set2 = new HashSet<>();
        set2.add(1);
        set2.add(2);

        Set<Integer> set3 = Sets.intersection(set2, set1);
        Set<Integer> set4 = Sets.difference(set2, set1);

        Long l1 = 2L;
        Long l2 = 5L;
        Long l3 = l1 - l2;

        System.out.println(l3);

       /*
        System.out.println("set1 : " +set1);
        System.out.println("set2 : " +set2);
        System.out.println(set3);
        System.out.println(Sets.difference(set2, set1).size()==0);*/




    }
}
