package algo.practice.a_interviewpractice.neetcode150.arraysandhashing;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, return true if the two strings are anagrams of each other, otherwise return false.
 *
 * An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.
 *
 * Example 1:
 *
 * Input: s = "racecar", t = "carrace"
 *
 * Output: true
 * Example 2:
 *
 * Input: s = "jar", t = "jam"
 *
 * Output: false
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if(s.length()!= t.length()) {
            return false;
        }
        Map<Character, Integer> charCountMap = new HashMap<>();
        for(char ch : s.toCharArray()) {
            if(charCountMap.containsKey(ch)){
                charCountMap.put(ch, charCountMap.get(ch)+1);
            } else {
                charCountMap.put(ch, 1);
            }
        }
        for(char ch2 : t.toCharArray()) {
            if(charCountMap.containsKey(ch2)) {
                if(charCountMap.get(ch2) > 1) {
                    charCountMap.put(ch2, charCountMap.get(ch2)-1);
                } else {
                    charCountMap.remove(ch2);
                }
            } else {
                return false;
            }
        }
        return charCountMap.isEmpty() ? true : false;
    }
}
