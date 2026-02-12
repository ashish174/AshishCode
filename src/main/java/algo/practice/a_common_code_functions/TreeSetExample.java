package algo.practice.a_common_code_functions;

import java.util.TreeSet;

public class TreeSetExample {
    public static void main(String[] args) {
        // Create a TreeSet
        TreeSet<String> treeSet = new TreeSet<>();

        // Add elements
        treeSet.add("Banana");
        treeSet.add("Apple");
        treeSet.add("Cherry");
        treeSet.add("Apple");   // Duplicate, will be ignored

        // Print elements (sorted)
        System.out.println("TreeSet contents (sorted):");
        for (String fruit : treeSet) {
            System.out.println(fruit);
        }

        // Get navigation features
        System.out.println("First: " + treeSet.first());      // Apple
        System.out.println("Last: " + treeSet.last());        // Cherry
        System.out.println("Greater than Apple: " + treeSet.higher("Apple")); // Banana
        System.out.println("Less than Cherry: " + treeSet.lower("Cherry"));   // Banana



        TreeSet<Integer> numbers = new TreeSet<>();

        // Add some numbers (TreeSet will sort them automatically)
        numbers.add(10);
        numbers.add(5);
        numbers.add(20);
        numbers.add(15);
        numbers.add(8);

        System.out.println("TreeSet (sorted): " + numbers); // [5, 8, 10, 15, 20]

        // Basic navigation
        System.out.println("First (smallest): " + numbers.first()); // 5
        System.out.println("Last (largest)  : " + numbers.last());  // 20

        // ceiling(): Smallest element >= value
        System.out.println("Ceiling of 9: " + numbers.ceiling(9));   // 10
        System.out.println("Ceiling of 15: " + numbers.ceiling(15)); // 15
        System.out.println("Ceiling of 21: " + numbers.ceiling(21)); // null (no such value)

        // floor(): Largest element <= value
        System.out.println("Floor of 9: " + numbers.floor(9));   // 8
        System.out.println("Floor of 15: " + numbers.floor(15)); // 15
        System.out.println("Floor of 4: " + numbers.floor(4));   // null (no such value)

        // higher(): strict greater
        System.out.println("Higher than 15: " + numbers.higher(15)); // 20

        // lower(): strict less
        System.out.println("Lower than 10: " + numbers.lower(10));   // 8

    }
}
