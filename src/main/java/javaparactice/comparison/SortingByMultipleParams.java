package javaparactice.comparison;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortingByMultipleParams {
    private static Logger logger = LoggerFactory.getLogger(SortingByMultipleParams.class);
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        Random random = new Random();
        for(int i= 0; i < 5; i++){
            studentList.add(new Student("Ash"+ random.nextInt(5), i, random.nextInt(50)));
        }
        logger.info("Unsorted List : {}", studentList);
        sortByNameAndAge(studentList);
        logger.info("Sorted List by name, age : {}", studentList);


        List<Student> sortedByAge = studentList.stream().sorted((o1, o2) -> o1.age - o2.age).collect(Collectors.toList());


    }

    public static void sortByNameAndAge(List<Student> studentList){
        Collections.sort(studentList, (o1, o2) -> {
            int compareTo = o1.name.compareTo(o2.name);
            if(compareTo == 0){
                return o1.age - o2.age;
            }
            return compareTo;
        });
    }

    public static void sortByNameAndAge1(List<Student> studentList){
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int compareTo = o1.name.compareTo(o2.name);
                if(compareTo == 0){
                    return o1.age - o2.age;
                }
                return compareTo;
            }
        });
    }

}

class Student {
    int id;
    String name;
    int age;
    int height;

    public Student(String name, int age, int height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}

