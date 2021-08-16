package algo.practice.dp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LPS(i,j) =  2 +  LPS(i+1, j-1)               if(char[i]==char[j])
 *          = Max {LPS(i+1, j), LPS(i, j-1)}    if(char[i]!=char[j])
 *          = 1 if (i==j)
 *          = 0 if (i>j)
 */
public class LongestPalindromicSubsequence {
    private static Logger logger = LoggerFactory.getLogger(LongestPalindromicSubsequence.class);

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
        int lpaSize = findLPSRecursive(myString.toCharArray(), 0, myString.length() - 1);
        logger.info("Longest Palindromic Subsequence for {} is of length : {}", myString, lpaSize);

    }
}
