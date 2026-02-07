package algo.practice.interviewpractice.neetcode150.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstring {
  /**
   * Returns the length of the longest substring without repeating characters.
   *
   * @param s the input string.
   * @return the length of the longest substring without repeating characters.
   *
   *
   * Input: s = "zxyzxyz"
   *
   * Output: 3
   *
   */
  public int lengthOfLongestSubstring(String s) {
        // Create a map to store the characters and their last seen indices.
        Map<Character, Integer> charPosition = new HashMap<>();

        // Initialize variables to store the maximum length and the start of the window.
        int maxLength = 0;
        int start = 0; // start of the window

        // Iterate through the string.
        for (int i = 0; i < s.length(); i++) {
            // Check if the current character is already in the map.
            if (charPosition.containsKey(s.charAt(i))) {
                // If it is, update the start of the window to be after the previous occurrence of the character.
                start = charPosition.get(s.charAt(i)) + 1;
            }

            // Update the last seen index of the current character in the map.
            charPosition.put(s.charAt(i), i);

            // Calculate the length of the current substring and update maxLength if necessary.
            maxLength = Math.max(maxLength, i - start + 1);
        }

        // Return the maximum length found.
        return maxLength;
    }


    //using sliding window
    public int lengthOfLongestSubstringCopy(String s) {
        if(s.length()<=1){
            return s.length();
        }
        Set<Character> charSet = new HashSet<>();
        int maxLength = 0;
        int i = 0;
        charSet.add(s.charAt(0));
        for(int j = 1; j < s.length(); j++){
            char ch = s.charAt(i);
            while(charSet.contains(ch)){
                charSet.remove(s.charAt(i));
                i++;
            }
            charSet.add(ch);
            maxLength = Math.max(maxLength, j - i + 1);
        }
        return maxLength;
    }


}
