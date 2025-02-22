package algo.practice.dp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A palindromic subsequence is a subsequence that reads the same backward as forward.
 *
 * Given a string,  we have to find the longest palindromic subsequence in this string
 * if the given sequence is “BBABCBCAB”,
 * then the output should be 7 as “BABCBAB” is the longest palindromic subsequence in it.
 * “BBBBB” and “BBCBB” are also palindromic subsequences of the given sequence, but not the longest ones.
 *
 *
 * LPS(i,j) = 1                                if (i==j)
 *          = 2 +  LPS(i+1, j-1)               if(char[i]==char[j])
 *          = Max {LPS(i+1, j), LPS(i, j-1)}    if(char[i]!=char[j])
 *          = 0 if (i>j)
 */
public class LongestPalindromicSubsequence {
    private static Logger logger = LoggerFactory.getLogger(LongestPalindromicSubsequence.class);
    private static int[][] memoization;
    private static int[][] tabulation;

    public static int findLPSByMemoization(char[] charString, int i, int j) {
        if (memoization[i][j] == 0) {
            if (i > j) {
                memoization[i][j] = 0;
            } else if (i == j) {
                memoization[i][j] = 1;
            } else if (charString[i] == charString[j]) {
                memoization[i][j] = 2 + findLPSByMemoization(charString, i + 1, j - 1);
            } else if (charString[i] != charString[j]) {
                memoization[i][j] = Math.max(findLPSByMemoization(charString, i + 1, j), findLPSByMemoization(charString, i, j - 1));
            }
        }
        return memoization[i][j];
    }

    public static int findLPSRecursive(char[] charString, int i, int j){
        if(i>j){
            return 0;
        }
        if(i==j){
            return 1;
        }
        if(charString[i]==charString[j]){
            return 2 + findLPSRecursive(charString, i+1, j-1);
        } else {
            return Math.max(findLPSRecursive(charString, i+1, j), findLPSRecursive(charString, i, j-1));
        }
    }

    public static void main(String[] args) {
        String myString = "geeksforgeeks";
        //String myString = "GEEKS FOR GEEKS";

        memoization = new int[myString.length() + 1][myString.length() + 1];
        int lpaSizeByMemoization = findLPSByMemoization(myString.toCharArray(), 0, myString.length() - 1);
        logger.info("Longest Palindromic Subsequence By Memoization for {} is of length : {}", myString, lpaSizeByMemoization);

        int lpaSizeByRecursion = findLPSRecursive(myString.toCharArray(), 0, myString.length() - 1);
        logger.info("Longest Palindromic Subsequence By Recursion for {} is of length : {}", myString, lpaSizeByRecursion);

    }
}
