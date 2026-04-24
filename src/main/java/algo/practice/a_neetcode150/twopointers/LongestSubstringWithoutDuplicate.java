package algo.practice.a_neetcode150.twopointers;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Longest Substring Without Repeating Characters
 * Problem Statement: Given a string s, find the length of the longest substring without repeating characters.
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Approach:
 * Uses a sliding window with a HashMap to track the last seen index of each character.
 * When a duplicate character is found, moves the start of the window past the previous occurrence of same character to maintain substring uniqueness.
 * Continuously updates the maximum length of unique substring found, resulting in an efficient O(n) solution.
 */
@Slf4j
public class LongestSubstringWithoutDuplicate {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>();
        int maxLength = 0;
        int start = 0; // start index of current window
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);

            // If we have seen this character and it's inside the current window,
            // move 'start' just after its last occurrence.
            if (lastIndex.containsKey(c) && lastIndex.get(c) >= start) {
                start = lastIndex.get(c) + 1;
            }

            // Update last seen index of this character
            lastIndex.put(c, i);

            // Current window length: i - start + 1
            maxLength = Math.max(maxLength, i - start + 1);
        }
        return maxLength;

    }

    public static void main(String[] args){
        LongestSubstringWithoutDuplicate longestSubstringWithoutDuplicate = new LongestSubstringWithoutDuplicate();
        log.info("{}", longestSubstringWithoutDuplicate.lengthOfLongestSubstring("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcdefghijk"));
    }
}
