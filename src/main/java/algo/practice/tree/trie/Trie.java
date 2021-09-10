package algo.practice.tree.trie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Trie is a special data structure used to store strings that can be visualized like a graph.
 * It consists of nodes and edges.
 * Each node consists of at max 26 children and edges connect each parent node to its children.
 * The root node of the trie always represents the null node.
 * Each child of nodes is sorted alphabetically.
 * Each node can have a maximum of 26 children (A to Z).
 * Each node (except the root) can store one letter of the alphabet.
 * Application :
 * 1. Autocompletion
 * 2. Pattern Searching
 * 3.
 */
public class Trie {
    private static final Logger LOGGER = LoggerFactory.getLogger(Trie.class);
    private static final int ALPHABETS_SIZE = 26;
    private static TrieNode root;


    public static void main(String[] args) {
        TrieNode root = new TrieNode();
        insert(root, "maria");
        insert(root, "mariana");
        search(root, "mariana");
        search(root, "maria");
        search(root, "marian");

    }

    public static void insert(TrieNode root, String word) {
        LOGGER.info("Inserting Word {}", word);
        int length = word.length();
        TrieNode trieNode = root;
        for (int level = 0; level < length; level++) {
            int charIndex = word.charAt(level) - 'a';
            if (trieNode.children[charIndex] == null) {
                trieNode.children[charIndex] = new TrieNode();
            }
            trieNode = trieNode.children[charIndex];
        }
        // Mark Last node as leaf or end of word
        trieNode.isEndOfWord = true;
        LOGGER.info("Word {} Inserted", word);
    }

    public static boolean search(TrieNode root, String word) {
        LOGGER.info("Searching Word {}", word);
        int length = word.length();
        TrieNode trieNode = root;
        for (int level = 0; level < length; level++) {
            int charIndex = word.charAt(level) - 'a';
            if (trieNode.children[charIndex] == null) {
                LOGGER.info("Word {} not present", word);
                return false;
            }
            trieNode = trieNode.children[charIndex];
        }
        if (trieNode.isEndOfWord) {
            LOGGER.info("Word {} present", word);
        } else {
            LOGGER.info("Word {} not present", word);
        }
        return trieNode.isEndOfWord;
    }


}


