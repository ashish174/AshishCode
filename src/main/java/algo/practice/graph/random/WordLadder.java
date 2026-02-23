package algo.practice.graph.random;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * You are given two words, beginWord and endWord, and also a list of words wordList. All of the given words are of the same length, consisting of lowercase English letters, and are all distinct.
 *
 * Your goal is to transform beginWord into endWord by following the rules:
 *
 * You may transform beginWord to any word within wordList, provided that at exactly one position the words have a different character, and the rest of the positions have the same characters.
 * You may repeat the previous step with the new word that you obtain, and you may do this as many times as needed.
 * Return the minimum number of words within the transformation sequence needed to obtain the endWord, or 0 if no such sequence exists.
 *
 * Example 1:
 * Input: beginWord = "cat", endWord = "sag", wordList = ["bat","bag","sag","dag","dot"]
 * Output: 4
 * Explanation: The transformation sequence is "cat" -> "bat" -> "bag" -> "sag".
 *
 * Example 2:
 * Input: beginWord = "cat", endWord = "sag", wordList = ["bat","bag","sat","dag","dot"]
 * Output: 0
 * Explanation: There is no possible transformation sequence from "cat" to "sag" since the word "sag" is not in the wordList.
 *
 * Approach:
 * - Treat each word as a node in a graph; two nodes are connected if they differ by exactly one character.
 * - To efficiently traverse, use BFS starting from the beginWord, attempting all 1-letter transformations at each step.
 * - Maintain a set for the wordList for O(1) lookup, and a set for visited words to avoid loops.
 * - For each word in BFS, try changing every character to 'a'-'z' and see if the new word is in the wordList and not visited yet.
 * - When the endWord is reached, return the level/steps of the BFS traversal.
 * - If endWord is not reached, return 0.
 * - Time Complexity: O(N * L^2), where N is wordList size and L is word length.
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Convert wordList to a set for O(1) lookups
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            // endWord must be in wordList, else impossible
            return 0;
        }

        // Use BFS queue. Each element is a pair (currentWord, currentLevel)
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        // Track visited words to avoid cycles
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int level = 1; // Begin at 1 (first word)

        while (!queue.isEmpty()) {
            int size = queue.size();
            // Process all words that are at the current BFS level
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) {
                    // Reached endWord, return steps
                    return level;
                }
                // Generate all possible 1-letter transformations
                for (int j = 0; j < word.length(); j++) {
                    char[] wordChars = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (wordChars[j] == c) continue; // Skip same letter
                        wordChars[j] = c;
                        String newWord = new String(wordChars);
                        // Only process words in the set and not already visited
                        if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                            queue.add(newWord);
                            visited.add(newWord);
                        }
                    }
                }
            }
            // Increment BFS level after expanding all words at current level
            level++;
        }

        // No valid transformation sequence found
        return 0;
    }

    // Simple main for demonstration
    public static void main(String[] args) {
        WordLadder solution = new WordLadder();
        List<String> wordList1 = Arrays.asList("bat","bag","sag","dag","dot");
        System.out.println(solution.ladderLength("cat", "sag", wordList1)); // Output: 4

        List<String> wordList2 = Arrays.asList("bat","bag","sat","dag","dot");
        System.out.println(solution.ladderLength("cat", "sag", wordList2)); // Output: 0
    }
}
