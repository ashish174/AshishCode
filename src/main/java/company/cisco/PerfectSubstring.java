package company.cisco;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PerfectSubstring {
    public static void main(String[] args) {
        System.out.println(perfectSubstring("1102021222", 2));
    }

    public static int perfectSubstring(String s, int k) {
        // Write your code here
        int perfectSubstringCount = 0;
        for(int i = 0 ; i <= s.length()-1; i++){
            for(int j = i+1; j <= s.length(); j++){
                String subString = s.substring(i, j);
                if(isPrefectSubstring(subString, k)){
                    System.out.println("Perfect "+subString);
                    perfectSubstringCount++;
                }
            }
        }
        return perfectSubstringCount;

    }

    private static boolean isPrefectSubstring(String s, int k){
        Map<Character, Integer> charmap = new HashMap<>();
        for(char ch : s.toCharArray()){
            charmap.put(ch, charmap.getOrDefault(ch, 0)+1);
        }
        for(char ch : charmap.keySet()){
            if(charmap.get(ch)!=k){
                return false;
            }
        }
        return true;
    }
}
