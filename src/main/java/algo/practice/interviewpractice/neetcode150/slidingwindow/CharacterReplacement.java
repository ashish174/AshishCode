package algo.practice.interviewpractice.neetcode150.slidingwindow;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * You are given a string s consisting of only uppercase english characters and an integer k. You can choose up to k characters of the string and replace them with any other uppercase English character.
 *
 * After performing at most k replacements, return the length of the longest substring which contains only one distinct character.
 *
 *Input: s = "XYYX", k = 2
 * Output: 4
 *
 * Input: s = "AAABABB", k = 1
 * Output: 5
 *
 *
 */
public class CharacterReplacement {
    // LengthOfWindow - higher freqeuncy <= k
    // LengthOfWindow = r-l+1
    public int characterReplacement(String s, int k) {
        int maxLength = 0;

        // Create a map to store the count of characters in the current window.
        Map<Character, Integer> charCountInWindowMap = new HashMap<>();
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
