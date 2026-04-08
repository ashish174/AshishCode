package algo.practice.a_common_code_functions;

import java.util.TreeMap;
import java.util.Map;

public class TreeMapExample {
    public static void main(String[] args) {
        // Create a TreeMap
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        // Add key-value pairs
        treeMap.put(3, "C");
        treeMap.put(1, "A");
        treeMap.put(4, "D");
        treeMap.put(2, "B");

        // Print keys in sorted order
        System.out.println("TreeMap contents (sorted by key):");
        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Find closest entries
        System.out.println("First Entry: " + treeMap.firstEntry()); // Smallest key
        System.out.println("Last Entry: " + treeMap.lastEntry());   // Largest key
        System.out.println("Entry for key <= 3: " + treeMap.floorEntry(3));
        System.out.println("Entry for key >= 3: " + treeMap.ceilingEntry(3));
        System.out.println("Higher Entry than 3: " + treeMap.higherEntry(3));
        System.out.println("Lower Entry than 3: " + treeMap.lowerEntry(3));

        //The set's iterator returns the keys in ascending order.
        System.out.println("Navigable Key set in asc order : " + treeMap.navigableKeySet());
        System.out.println("Key set in desc order " + treeMap.descendingKeySet());


        System.out.println("Remove and return first entry " + treeMap.pollFirstEntry());
        System.out.println("Remove first entry and return value " + treeMap.remove(treeMap.firstKey()));
        System.out.println("Remove and return last entry " + treeMap.pollLastEntry());
        System.out.println("Remove last entry and return value " + treeMap.remove(treeMap.lastKey()));
    }
}
