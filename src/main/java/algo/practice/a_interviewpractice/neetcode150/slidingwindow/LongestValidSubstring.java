package algo.practice.a_interviewpractice.neetcode150.slidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given a string word and an array of strings forbidden.
 * A string is called valid if none of its substrings are present in forbidden.
 * Return the length of the longest valid substring of the string word.
 * A substring is a contiguous sequence of characters in a string, possibly empty.
 * Example 1:
 *
 * Input: word = "cbaaaabc", forbidden = ["aaa","cb"]
 * Output: 4
 * Explanation: There are 11 valid substrings in word: "c", "b", "a", "ba", "aa", "bc", "baa", "aab", "ab", "abc" and "aabc". The length of the longest valid substring is 4.
 * It can be shown that all other substrings contain either "aaa" or "cb" as a substring.
 * Example 2:
 *
 * Input: word = "leetcode", forbidden = ["de","le","e"]
 * Output: 4
 * Explanation: There are 11 valid substrings in word: "l", "t", "c", "o", "d", "tc", "co", "od", "tco", "cod", and "tcod". The length of the longest valid substring is 4.
 * It can be shown that all other substrings contain either "de", "le", or "e" as a substring.
 *
 * Approach:
 * - Use a sliding window (two pointers) and iterate through the string.
 * - For each ending position (`right`), move the `left` pointer rightward if the substring between `left` and `right` contains a forbidden word.
 *   - Only check substrings ending at `right` of length up to the longest forbidden word.
 * - For fast lookup, store forbidden words in a HashSet.
 * - At each step, track and update the max valid window size.
 * - Time Complexity: O(n * L), where n = word length and L = length of longest forbidden word
 *
 */
public class LongestValidSubstring {
    public int longestValidSubstring(String word, String[] forbidden) {
        Set<String> forbiddenSet = new HashSet<>();
        int maxForbiddenLen = 0;
        // Add each forbidden words to set, record the length of the longest
        for (String f : forbidden) {
            forbiddenSet.add(f);
            maxForbiddenLen = Math.max(maxForbiddenLen, f.length());
        }

        int maxLen = 0;
        int left = 0; // Start of sliding window

        // Iterate right pointer over string
        for (int right = 0; right < word.length(); right++) {
            // For each position, check suffixes of lengths up to maxForbiddenLen
            for (int len = 1; len <= maxForbiddenLen && right - len + 1 >= left; len++) {
                String substr = word.substring(right - len + 1, right + 1);
                if (forbiddenSet.contains(substr)) {
                    // Slide left past this forbidden substring
                    left = right - len + 2;
                }
            }
            // Update maxLen with the current window
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    // Simple test
    public static void main(String[] args) {
        LongestValidSubstring solution = new LongestValidSubstring();
        System.out.println(solution.longestValidSubstring("cbaaaabc", new String[]{"aaa","cb"})); // Output: 4
        System.out.println(solution.longestValidSubstring("leetcode", new String[]{"de","le","e"})); // Output: 4
    }

}
