package algo.practice.a_interviewpractice.neetcode150.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a string s consisting of only uppercase english characters and an integer k.
 * You can choose up to k characters of the string and replace them with any other uppercase English character.
 *
 * After performing at most k replacements, return the length of the longest substring which contains only one distinct character.
 *
 *Input: s = "XYYX", k = 2
 * Output: 4 (i.e. XXXX)
 *
 * Input: s = "AAABABB", k = 1
 * Output: 5 (i.e. AAAAA)
 *
 *
 * Input: s = "AABCBABB", k = 1
 * Output: 5 (i.e. AAAAA)
 *
 * Approach:
 * Use the sliding window technique: expand the window by moving the right pointer,
 * counting the frequency of each character in the current window, and tracking the count of the most frequent character seen so far.
 * If the number of characters we need to replace (window size minus max frequency) is more than k,
 * move the left pointer to shrink the window. Track and return the largest window that satisfies the condition.
 */
public class CharacterReplacement {
    // LengthOfWindow - higher freqeuncy <= k
    // LengthOfWindow = r-l+1
    public int characterReplacement(String s, int k) {
        int maxLength = 0;

        // Create a map to store the count of characters in the current window.
        Map<Character, Integer> charCountInWindowMap = new HashMap<>();
        // Track highest frequency in given window
        // Initialize a variable to store the maximum frequency of any character in the current window.
        int maxFrequency = 0;
        int l = 0;
        // Iterate through the string using the right pointer of the sliding window.
        for(int r=0; r < s.length(); r++) {
            char ch = s.charAt(r);
            // Increment the count of the character in the map.
            charCountInWindowMap.put(ch, charCountInWindowMap.getOrDefault(ch, 0)+1 );
            // Update the maximum frequency if the count of the current character is greater.
            maxFrequency = Math.max(maxFrequency, charCountInWindowMap.get(ch));
            //windowSize = r-l+1;
            //IF windowSize - maxFrequency > k, then we have to keep shifting left pointer
            // If it is, we need to shrink the window by moving the left pointer to the right.
            while((r-l+1) - maxFrequency > k) {
                // Decrement the count of the character at the left pointer in the map.
                // We are not updating maxFrequency. Even if maxFrequency may go temporarily stale (overestimates the true maximum in the window),
                // the window shrinks conservatively (never allows more than k replacements), so the result isn’t invalid
                charCountInWindowMap.put(s.charAt(l), charCountInWindowMap.get(s.charAt(l))-1);
                l++;
            }
            //since you find a window satisfying k, update the maxlength if current window length is bigger
            maxLength = Math.max(maxLength, r-l+1);
        }
        return maxLength;
    }

    public static void main(String[] args){
        new CharacterReplacement().characterReplacement("AAABABB", 2);
    }
}
