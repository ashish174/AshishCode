package algo.practice.dp;

import algo.practice.arrays.PrintArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * You can use three action on character - to convert one String to other string : Insert, Remove, Replace
 * Convert into minimum steps
 * Appln:- display all the words in a dictionary that are near proximity to a given word or a incorrectly spelled word
 * Also used in Lucene Search Engine. Lucene includes a feature to perform a fuzzy search based on edit distance.
 * Apache Lucene is imported by several apps like Elastic Search, Apache Solr, Mongo DB, Open Search
 *
 *
 * E(m,n) = Edit distance for string/char of size m & size n
 *
 * E(i,j)   = E(i-1, j-1)               , if (a[i]==b[j])
 *          = 1 + min{                  , if (a[i]!=b[j])
 *              Insert -    E(i, j-1),
 *              Remove -    E(i-1,j),
 *              Replace -   E(i-1,j-1)
 *              }
 *
 */
public class EditDistance {
    public static final Logger LOGGER = LoggerFactory.getLogger(EditDistance.class);
    public static int[][] tabulation;
    public static int[][] memoization;


    public static int findEditDistanceByMemoization(char[] fArr, char[] sArr, int m, int n) {
        if (memoization[m][n] == 0) {
            if (m == 0) {
                memoization[m][n] = n;
            } else if (n == 0) {
                memoization[m][n] = m;
            } else if (fArr[m - 1] == sArr[n - 1]) {
                memoization[m][n] = findEditDistanceByMemoization(fArr, sArr, m - 1, n - 1);
            } else {
                memoization[m][n] = 1 + findMin(findEditDistanceByMemoization(fArr, sArr, m, n - 1),
                        findEditDistanceByMemoization(fArr, sArr, m - 1, n),
                        findEditDistanceByMemoization(fArr, sArr, m - 1, n - 1));
            }
        }
        return memoization[m][n];
    }

    public static int findEditDistanceByTabulation(char[] fArr, char[] sArr, int m, int n) {
        tabulation = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    tabulation[i][j] = j;
                } else if (j == 0) {
                    tabulation[i][j] = i;
                } else if (fArr[i - 1] == sArr[j - 1]) {
                    tabulation[i][j] = tabulation[i - 1][j - 1];
                } else if (fArr[i - 1] != sArr[j - 1]) {
                    tabulation[i][j] = 1 + findMin(tabulation[i][j - 1], tabulation[i - 1][j], tabulation[i - 1][j - 1]);
                }

            }
        }
        return tabulation[m - 1][n - 1];
    }

    public static int findEditDistanceRecursive(char[] fArr, char[] sArr, int m, int n) {
        if (m == 0) {
            return n;
        }
        if (n == 0) {
            return m;
        }
        if (fArr[m - 1] == sArr[n - 1]) {
            return findEditDistanceRecursive(fArr, sArr, m - 1, n - 1);
        } else {
            return 1 + findMin(findEditDistanceRecursive(fArr, sArr, m, n - 1),
                    findEditDistanceRecursive(fArr, sArr, m - 1, n),
                    findEditDistanceRecursive(fArr, sArr, m - 1, n - 1));
        }
    }

    public static int findMin(int a, int b, int c){
        return Math.min(Math.min(a,b), c);
    }

    public static void main(String[] args) {
        char[] fArr = "sunday".toCharArray();
        char[] sArr = "saturday".toCharArray();
        int editDistanceRecursive = findEditDistanceRecursive(fArr, sArr, fArr.length, sArr.length);
        LOGGER.info("Minimum Edit Distance by Recursion from \"{}\" to \"{}\" is : {}", String.valueOf(fArr), String.valueOf(sArr), editDistanceRecursive);

        int editDistanceByTabulation = findEditDistanceByTabulation(fArr, sArr, fArr.length, sArr.length);
        LOGGER.info("Minimum Edit Distance by tabulation from \"{}\" to \"{}\" is : {}", String.valueOf(fArr), String.valueOf(sArr), editDistanceByTabulation);
        PrintArray.print2DSquareMatrix(tabulation);

        memoization = new int[fArr.length + 1][sArr.length + 1];
        int editDistanceByMemoization = findEditDistanceByMemoization(fArr, sArr, fArr.length, sArr.length);
        LOGGER.info("Minimum Edit Distance by memoization from \"{}\" to \"{}\" is : {}", String.valueOf(fArr), String.valueOf(sArr), editDistanceByMemoization);
        PrintArray.print2DSquareMatrix(memoization);
    }

}





