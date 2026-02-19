package algo.practice.arrays;

/**
 * Given a m x n binary matrix mat, find the 0-indexed position of the row that contains the maximum count of ones, and the number of ones in that row.
 *
 * In case there are multiple rows that have the maximum count of ones, the row with the smallest row number should be selected.
 *
 * Return an array containing the index of the row, and the number of ones in it.
 *
 *
 *
 * Example 1:
 *
 * Input: mat = [[0,1],[1,0]]
 * Output: [0,1]
 * Explanation: Both rows have the same number of 1's. So we return the index of the smaller row, 0, and the maximum count of ones (1). So, the answer is [0,1].
 * Example 2:
 *
 * Input: mat = [[0,0,0],[0,1,1]]
 * Output: [1,2]
 * Explanation: The row indexed 1 has the maximum count of ones (2). So we return its index, 1, and the count. So, the answer is [1,2].
 * Example 3:
 *
 * Input: mat = [[0,0],[1,1],[0,0]]
 * Output: [1,2]
 * Explanation: The row indexed 1 has the maximum count of ones (2). So the answer is [1,2].
 *
 * Approach:
 * Iterate through each row of the matrix and count the number of ones.
 * Track the row with the highest count and update the result if a new max is found.
 * If there is a tie, choose the row with the smaller index (naturally handled by checking rows in order).
 * Time complexity: O(m * n), where m is the number of rows and n is the number of columns.
 *
 */
public class RowWithMaximumOnes {
    public int[] rowAndMaximumOnes(int[][] mat) {
        int maxRowIdx = 0;     // Index of row with maximum number of ones
        int maxOnes = 0;       // Maximum count of ones found so far

        for (int i = 0; i < mat.length; i++) {
            int onesCount = 0;
            // Count number of ones in current row
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 1) {
                    onesCount++;
                }
            }
            // Update maxOnes and maxRowIdx if this row has more ones
            if (onesCount > maxOnes) {
                maxOnes = onesCount;
                maxRowIdx = i;
            }
            // If there is a tie, keep the lower row index (handled by not updating on tie)
        }
        // Return result as [row index, count of ones]
        return new int[]{maxRowIdx, maxOnes};

    }
}
