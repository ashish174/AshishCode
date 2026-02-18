package algo.practice.a_interviewpractice.neetcode150.twopointers;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

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
                length = i - 1 - charPosition.get(s.charAt(i));
                charPosition.put(s.charAt(i), i);
            }
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
