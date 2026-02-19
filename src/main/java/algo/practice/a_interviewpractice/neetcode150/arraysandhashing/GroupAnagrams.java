package algo.practice.a_interviewpractice.neetcode150.arraysandhashing;

/**
 * Given an array of strings strs, group all anagrams together into sublists. You may return the output in any order.
 *
 * An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.
 *
 * Example 1:
 *
 * Input: strs = ["act","pots","tops","cat","stop","hat"]
 *
 * Output: [["hat"],["act", "cat"],["stop", "pots", "tops"]]
 * Example 2:
 *
 * Input: strs = ["x"]
 *
 * Output: [["x"]]
 * Example 3:
 *
 * Input: strs = [""]
 *
 * Output: [[""]]
 *
 /**
 * Approach:
 * - For each string in the input, sort its characters; use the sorted string as a key.
 * - All anagrams will have the same sorted key and are grouped together in a map.
 * - Collect and return the values of the map as the anagram groups.
 * - Time Complexity: O(N * K log K), where N = number of strings and K = max string length.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        // Map: sorted string -> list of original anagrams
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            // Sort the characters in the string to form the key
            char[] charArr = s.toCharArray();
            Arrays.sort(charArr);
            String key = new String(charArr);

            // Add to map, creating a new list if key does not exist
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        // Return the grouped anagrams by collecting the map's values
        return new ArrayList<>(map.values());
    }

    // Example usage and test
    public static void main(String[] args) {
        GroupAnagrams solution = new GroupAnagrams();
        String[] strs1 = {"act","pots","tops","cat","stop","hat"};
        System.out.println(solution.groupAnagrams(strs1)); // [["act","cat"],["pots","tops","stop"],["hat"]]

        String[] strs2 = {"x"};
        System.out.println(solution.groupAnagrams(strs2)); // [["x"]]

        String[] strs3 = {""};
        System.out.println(solution.groupAnagrams(strs3)); // [[""]]
    }
}
