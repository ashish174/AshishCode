package algo.practice.a_interviewpractice.neetcode150.twopointers;

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
        Map<Character, Integer> charPosition = new HashMap<>();
        int maxLength = 0;
        int length = 0;
        for(int i=0; i<s.length(); i++){
            if(!charPosition.containsKey(s.charAt(i))) {
                charPosition.put(s.charAt(i), i);
            } else {
                //recalculate the window size by skipping start_indx till last occurence
                length = i - 1 - charPosition.get(s.charAt(i));
                charPosition.put(s.charAt(i), i);
            }
            //add curr elem (i) to the window
            length++;
            maxLength = Math.max(maxLength, length);
        }
        return maxLength;

    }

    public static void main(String[] args){
        LongestSubstringWithoutDuplicate longestSubstringWithoutDuplicate = new LongestSubstringWithoutDuplicate();
        log.info("{}", longestSubstringWithoutDuplicate.lengthOfLongestSubstring("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcdefghijk"));
    }
}
