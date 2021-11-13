package algo.practice.google;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubSequence {
    public static final Logger LOGGER = LoggerFactory.getLogger(SubSequence.class);

    public static void main(String[] args) {
        String A = "rabbbit";
        String B = "rabbit";
        int[][] memoization = new int[A.length() + 1][B.length() + 1];
        int subsequenceCount = countSubsequence(A, B, A.length(), B.length(), memoization);
        LOGGER.info("Count of Subsequenec {} in String {} is {}", B, A, subsequenceCount);
    }


    /**
     * C(m,n)   = C(m-1, n-1) + C(m-1, n) , if A[m] = B[n]
     * = C(m-1, n), if A[m] != B[n]
     * = 1 if n == 0
     * <p>
     * These are the possible removals of characters:
     * => A = "ra_bbit"
     * => A = "rab_bit"
     * => A = "rabb_it"
     *
     * @param myString
     * @param subSequence
     * @return
     */
    public static int countSubsequence(String myString, String subSequence, int m, int n, int[][] memoization) {
        if (memoization[m][n] == 0) {
            if (n == 0) {
                memoization[m][n] = 1;
            } else if (m == 0) {
                memoization[m][n] = 0;
            } else if (myString.charAt(m - 1) == subSequence.charAt(n - 1)) {
                memoization[m][n] = countSubsequence(myString, subSequence, m - 1, n - 1, memoization)
                        + countSubsequence(myString, subSequence, m - 1, n, memoization);
            } else if (myString.charAt(m - 1) != subSequence.charAt(n - 1)) {
                memoization[m][n] = countSubsequence(myString, subSequence, m - 1, n, memoization);
            }
        }
        return memoization[m][n];
    }
}
