package javaparactice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

// Do a java 8 stream map(transform), then flatten
//
public class FlatMap {
    public static void main(String[] args) {
        List<List<String>> listOfStringList = new ArrayList<>();
        listOfStringList.add(Arrays.asList("a", "d", "f"));
        listOfStringList.add(Arrays.asList("p", "q", "r"));
        listOfStringList.add(Arrays.asList("x", "y", "z"));
        System.out.println(listOfStringList);
        List<String> flattenedStringList = listOfStringList.stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
        System.out.println(listOfStringList);
        System.out.println(flattenedStringList);
    }
}
