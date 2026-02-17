package algo.practice.interviewpractice.neetcode150.twopointers;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a string s consisting only lowercase alphabets and an integer k. Your task is to find the length of the longest substring that contains exactly k distinct characters.
 *
 * Note : If no such substring exists, return -1.
 *
 * Examples:
 * Input: s = "aabacbebebe", k = 3
 * Output: 7
 * Explanation: The longest substring with exactly 3 distinct characters is "cbebebe", which includes 'c', 'b', and 'e'.
 *
 */
public class LongestSubstringWithKUniques {
    public int longestKSubstr(String s, int k) {
        // Edge case: impossible cases
        if (k > s.length() || s.length() == 0 || k == 0) {
            return -1;
        }

        // Map to count occurrences of each character in the window
        Map<Character, Integer> charCount = new HashMap<>();
        int maxLen = -1;
        int left = 0; // Left pointer of window

        // Expand the window with right pointer
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            // Add current character to the map, increase its count
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);

            // Shrink the window from the left until we have at most k distinct characters
            while (charCount.size() > k) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                // Remove character from map if its count is now zero
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                left++; // Move left boundary to the right
            }

            // If the window contains exactly k unique characters, update the maximum length
            if (charCount.size() == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        return maxLen;
    }


    public int longestKSubstrCpy(String s, int k) {
        if(k > s.length() || s.length()==0 || k==0) {
            return -1;
        }
        if((s.length()==1 && k==1)){
            return 1;
        }
        int globalMaxLength = -1;

        int left = 0;
        int distinctRemaining = k;
        Map<Character, Integer> charCount = new HashMap<>();
        charCount.put(s.charAt(left), 1);
        distinctRemaining--;
        for(int right=1; right < s.length(); right++){
            char ch = s.charAt(right);
            //char is already counted in existing k
            if(charCount.containsKey(ch) && charCount.get(ch)!=0){
                charCount.put(ch, charCount.get(ch)+1);
            } else {
                //char is not counted in existing k.
                //If distinctRemaining = 0, then replenish distinctRemaining by popping from left
                if(distinctRemaining <= 0) {
                    while(distinctRemaining<=0){
                        char chToPop = s.charAt(left);
                        left++;
                        charCount.put(chToPop, charCount.get(chToPop)-1);
                        if(charCount.get(chToPop)==0){
                            distinctRemaining++;
                            break;
                        }
                    }
                }
                charCount.put(ch, charCount.getOrDefault(ch, 0)+1);
                distinctRemaining--;
            }
            // If the window contains exactly k unique characters, update the maximum length
            if(distinctRemaining==0){
                globalMaxLength = Math.max(globalMaxLength, right-left+1);
            }
        }
        return globalMaxLength;

    }

}
