package algo.practice.dp;

import algo.practice.arrays.PrintArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LCS of “ABCDGH” and “AEDFHR” = “ADH” of length 3.
 * LCS(x,y) = LCS of arrays of length x & y
 * <p>
 * Optimal substructure:-
 * LCS(i,j)
 * = 1 + LCS(i-1, j-1) ,if a[i]=b[j]
 * = Max{LCS(i-1, j), LCS(i, j-1)} ,if a[i] != b[j]
 * = 0 if i <0 || j <0
 */
public class LCS {
    public static final Logger LOGGER = LoggerFactory.getLogger(LCS.class);
    public static int[][] tabulation;
    public static int[][] memoization;


    /**
     * Time Complexity - O(mn)
     * Space Complexity - O(mn)
     *
     * @param fArr
     * @param sArr
     * @param m
     * @param n
     * @return
     */
    public static int findLCSLengthUsingTabulation(char[] fArr, char[] sArr, int m, int n) {
        tabulation = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    tabulation[i][j] = 0;
                } else if (fArr[i - 1] == sArr[j - 1]) {
                    tabulation[i][j] = 1 + tabulation[i - 1][j - 1];
                } else {
                    tabulation[i][j] = Math.max(tabulation[i - 1][j], tabulation[i][j - 1]);
                }
            }
        }
        return tabulation[m][n];
    }

    /**
     * Time Complexity - O(mn)
     * Space Complexity - O(mn)
     *
     * @param fArr
     * @param sArr
     * @param m
     * @param n
     * @return
     */
    public static int findLCSLengthUsingMemoization(char[] fArr, char[] sArr, int m, int n) {
        if (memoization[m][n] == 0) {
            if (m == 0 || n == 0) {
                return 0;
            } else if (fArr[m - 1] == sArr[n - 1]) {
                memoization[m][n] = 1 + findLCSLengthUsingMemoization(fArr, sArr, m - 1, n - 1);
            } else {
                memoization[m][n] = Math.max(findLCSLengthUsingMemoization(fArr, sArr, m - 1, n), findLCSLengthUsingMemoization(fArr, sArr, m, n - 1));
            }
        }
        return memoization[m][n];
    }

    /**
     * Time Complexity - O(n2^n)
     * Space Complexity - O(mn)
     *
     * @param fArr
     * @param sArr
     * @param m
     * @param n
     * @return
     */
    public static int findLCSLengthRecursive(char[] fArr, char[] sArr, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (fArr[m - 1] == sArr[n - 1]) {
            return 1 + findLCSLengthRecursive(fArr, sArr, m - 1, n - 1);
        } else {
            return Math.max(findLCSLengthRecursive(fArr, sArr, m - 1, n), findLCSLengthRecursive(fArr, sArr, m, n - 1));
        }
    }

    public static char[] printLCS(char[] fArr, char[] sArr, int[][] lcs) {
        int i = fArr.length;
        int j = sArr.length;
        int lcsLength = lcs[i][j];
        char[] lcsCharArr = new char[lcsLength];
        while (lcs[i][j] != 0) {
            int maxFromLeftOrTop = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
            if (lcs[i][j] > maxFromLeftOrTop) {
                // This char is part of lcs. Include it & move diagonally up
                lcsCharArr[--lcsLength] = fArr[i - 1];
                //Move diagonally up
                i--;
                j--;
            } else {
                if (lcs[i][j] == lcs[i - 1][j]) {
                    // move left
                    i--;
                } else {
                    //move up
                    j--;
                }
            }
        }
        return lcsCharArr;
    }

    public static void main(String[] args) {
        //char[] firstArr = "ABCDGH".toCharArray();
        //char[] secondArr = "AEDFHR".toCharArray();
        char[] firstArr = "AGGTAB".toCharArray();
        char[] secondArr = "GXTXAYB".toCharArray();
        //Recursive approach
        int lcsLength = findLCSLengthRecursive(firstArr, secondArr, firstArr.length, secondArr.length);
        LOGGER.info("LCS lenght using Recursion for arrays {} & {} is : {}", firstArr, secondArr, lcsLength);
        //Tabulation
        int lcslength1 = findLCSLengthUsingTabulation(firstArr, secondArr, firstArr.length, secondArr.length);
        LOGGER.info("LCS lenght for using tabulation  arrays {} & {} is : {}", firstArr, secondArr, lcslength1);
        PrintArray.print2DSquareMatrix(tabulation);
        //Memoization
        memoization = new int[firstArr.length + 1][secondArr.length + 1];
        int lcslength2 = findLCSLengthUsingMemoization(firstArr, secondArr, firstArr.length, secondArr.length);
        LOGGER.info("LCS lenght for using memoization  arrays {} & {} is : {}", firstArr, secondArr, lcslength1);
        PrintArray.print2DSquareMatrix(memoization);
        char[] lcsCharArr = printLCS(firstArr, secondArr, memoization);
        LOGGER.info("LCS for using memoization  arrays {} & {} is : {} with length {}", firstArr, secondArr, lcsCharArr, lcslength1);

    }
}
