package company.microsoft;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public static void main(String[] args) {
        String S = "CAT";
        String[] L = {"ILOVEMYDOG", "CATS"};
        Solution solution = new Solution();
        System.out.println(solution.solution(S, L));
    }

    public int solution(String S, String[] L) {
        // write your code in Java SE 8
        Map<Character, Integer> completeCharMap = getCharCountMap(S);
        int maxCount = 0;
        for (String boardString : L) {
            Map<Character, Integer> boardStringCharMap = getCharCountMap(boardString);
            maxCount = Math.max(maxCount, findWordMaxCount(completeCharMap, boardStringCharMap));
        }
        return maxCount;

    }

    Map<Character, Integer> getCharCountMap(String string) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char ch : string.toCharArray()) {
            charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
        }
        return charCountMap;
    }

    int findWordMaxCount(Map<Character, Integer> completeCharMap, Map<Character, Integer> boardStringCharMap) {
        int currentBoardStringCharCount = 0;
        int currentCompleteCharCount = 0;
        int wordCount = Integer.MAX_VALUE;
        for (char ch : boardStringCharMap.keySet()) {
            currentBoardStringCharCount = boardStringCharMap.getOrDefault(ch, 0);
            currentCompleteCharCount = completeCharMap.getOrDefault(ch, 0);
            if (currentBoardStringCharCount < currentCompleteCharCount) {
                wordCount = Math.min(wordCount, currentCompleteCharCount / currentBoardStringCharCount);
            } else {
                wordCount = 0;
            }
        }
        return wordCount;
    }
}
