package algo.practice.dp.typeLIS;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Input: Given string :"forgeeksskeegfor",
 * Output: "geeksskeeg"
 *
 * Input: Given string :"Geeks",
 * Output: "ee"
 *
 *
 */
@Slf4j
public class LongestPalindromeSubString {

    public static String longestPalindrome(String s) {
        if (StringUtils.isEmpty(s) || s.length() == 1) {
            return s;
        }
        int size = s.length();
        char[] strChar = s.toCharArray();
        char[] reverseStrChar = new StringBuilder(s).reverse().toString().toCharArray();
        int[][] dp = new int[size + 1][size + 1];
        int maxPalindromeSize = 0;
        int index_y = -1;

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (reverseStrChar[i - 1] == strChar[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (maxPalindromeSize < dp[i][j]) {
                        maxPalindromeSize = dp[i][j];
                        index_y = j-1;
                    }
                }
            }
        }
        if (maxPalindromeSize != 0) {
            int st_index = index_y - maxPalindromeSize + 1;
            int end_index = index_y;
            log.info("Max Palindrome Size is {} from indx : {} ->  {}",maxPalindromeSize, st_index, end_index);
            return s.substring(st_index, end_index+1);
        }
        return null;
    }

    public static void main(String[] args) {
        String s = "babadab";
        String longestPalindrome = LongestPalindromeSubString.longestPalindrome(s);
        log.info("Longest Palindromic Subsequence for "+ s + " : "+ longestPalindrome);

        log.info("Longest palindrome length : {}", new LongestPalindromeSubString().longestPalindromelength(s));

    }


    public int longestPalindromelength(String s) {
        int n = s.length();
        int[][] memoization = new int[n+1][n+1];
        return findLPS(s, 1, n, memoization);
    }

    int findLPS(String s, int i, int j, int[][] memoization){
        if(i==j) {
            return 1;
        }
        if(memoization[i][j]==0) {
            if(s.charAt(i-1)==s.charAt(j-1)) {
                memoization[i][j] = 2 + findLPS(s, i+1, j-1, memoization);
            } else {
                memoization[i][j] = Math.max(findLPS(s, i+1, j, memoization), findLPS(s, i, j-1, memoization) );
            }
        }
        return memoization[i][j];
    }

}
