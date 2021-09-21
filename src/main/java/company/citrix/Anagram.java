package company.citrix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * "abc" , "cba", "cbad"
 * ""
 */
public class Anagram {
    private static Anagram anagram;


    public static Anagram getMyClassInstance() {
        if (anagram == null) {
            synchronized (Anagram.class) {
                if (anagram == null) {
                    anagram = new Anagram();
                }
            }
        }
        return anagram;
    }


    public boolean isAnagram(String firstString, String secondString) {
        if (firstString == null && secondString == null) {
            return true;
        }
        if (firstString == null || secondString == null || (firstString.length() != secondString.length())) {
            return false;
        }
        List<Character> charList = Arrays.asList('a', 'a', 'b', 'd');
        //charList.stream().collect(Collectors.toMap(ch -> ch , ))
        Map<Character, Integer> charCountMap = getCharCountMap(firstString);
        for (char ch : secondString.toCharArray()) {
            if (!charCountMap.containsKey(ch)) {
                return false;
            }
            Integer count = charCountMap.get(ch);
            if (count == 1) {
                charCountMap.remove(ch);
            } else {
                charCountMap.put(ch, count - 1);
            }
        }
        return true;
    }

    private Map<Character, Integer> getCharCountMap(String firstString) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char ch : firstString.toCharArray()) {
            charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
        }
        return charCountMap;
    }


}
