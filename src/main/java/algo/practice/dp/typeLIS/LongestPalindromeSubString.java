package algo.practice.dp.typeLIS;

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
public class LongestPalindromeSubString {
    private static Logger logger = LoggerFactory.getLogger(LongestPalindromeSubString.class);

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
            logger.info("Max Palindrome Size is {} from indx : {} ->  {}",maxPalindromeSize, st_index, end_index);
            return s.substring(st_index, end_index+1);
        }
        return null;
    }

    public static void main(String[] args) {
        String s = "babadab";
        String longestPalindrome = LongestPalindromeSubString.longestPalindrome(s);
        logger.info("Longest Palindromic Subsequence for "+ s + " : "+ longestPalindrome);

    }
}
