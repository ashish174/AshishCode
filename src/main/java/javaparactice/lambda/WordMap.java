package javaparactice.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordMap {
    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("anshu", "billu", "billu", "juli", "billu", "juli");
        Map<String, Long> stringLongMap = stringList.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"ashish"));
        personList.add(new Person(2,"anshu"));
        personList.add(new Person(3,"juli"));

        personList.stream().collect(Collectors.toMap(p -> p.id, p -> p));

        System.out.println("hiii");

    }


}

class Person {
    int id;
    String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
}