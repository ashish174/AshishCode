package algo.practice.a_interviewpractice.neetcode150.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 * Here we are not asking length, but the substring itself.
 * The testcases will be generated such that the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 *
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 *
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 *
 * Approach:
 * Use the sliding window technique to find the minimum window in 's' containing all characters of 't'.
 * - Build a frequency map for all characters in 't'.
 * - Expand the window by moving the right pointer, updating character counts in the window.
 * - When the window contains all characters in 't' with required frequencies, try shrinking from the left to minimize the window.
 * - Keep track of the smallest valid window found throughout the process.
 * - Return the smallest window or an empty string if no such window exists.
 *
 *
 */
public class MinWindowSubstringWithAllCharacters {
    public String minWindow(String s, String t) {
        // Edge case: t is longer than s
        if (s.length() < t.length()) return "";

        // Map to keep count of characters in t
        Map<Character, Integer> tFreq = new HashMap<>();
        for (char ch : t.toCharArray()) {
            tFreq.put(ch, tFreq.getOrDefault(ch, 0) + 1);
        }

        // Number of unique characters in t to be matched in window
        int required = tFreq.size();
        int formed = 0; // Characters meeting required freq in current window

        // Window pointers and result variables
        int l = 0, r = 0; // left and right pointers
        int minLen = Integer.MAX_VALUE;
        //start position of min window
        int minStart = 0;

        // Map to keep count of characters in current window
        Map<Character, Integer> windowFreq = new HashMap<>();

        while (r < s.length()) {
            char c = s.charAt(r);
            windowFreq.put(c, windowFreq.getOrDefault(c, 0) + 1);

            // If current char is in t and window has enough of it, increase 'formed'
            if (tFreq.containsKey(c) && windowFreq.get(c).intValue() == tFreq.get(c).intValue()) {
                formed++;
            }

            // Try to shrink from the left as much as possible while window is valid
            while (l <= r && formed == required) {
                // Update minimum window if this window is smaller
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    minStart = l;
                }

                // Prepare to move l ahead and update windowFreq
                char leftChar = s.charAt(l);
                windowFreq.put(leftChar, windowFreq.get(leftChar) - 1);

                // If a required char falls below required count, window is not valid
                if (tFreq.containsKey(leftChar) && windowFreq.get(leftChar) < tFreq.get(leftChar)) {
                    formed--;
                }

                l++; // shrink window from the left
            }

            r++; // expand window to the right
        }

        // If minLen is not updated, no such window exists
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}
