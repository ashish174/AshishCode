package algo.practice.arrays;

/**
 * You are given a 2D binary array arr[][] consisting of only 1s and 0s. Each row of the array is sorted in non-decreasing order. Your task is to find and return the index of the first row that contains the maximum number of 1s. If no such row exists, return -1.
 *
 * Note:
 *
 * The array follows 0-based indexing.
 * The number of rows and columns in the array are denoted by n and m respectively.
 * Examples:
 *
 * Input: arr[][] = [[0,1,1,1], [0,0,1,1], [1,1,1,1], [0,0,0,0]]
 * Output: 2
 * Explanation: Row 2 contains the most number of 1s (4 1s). Hence, the output is 2.
 *
 *  0 1 1 1
 *  0 0 1 1
 *  1 1 1 1
 *  0 0 0 0
 *
 * Input: arr[][] = [[0,0], [1,1]]
 * Output: 1
 * Explanation: Row 1 contains the most number of 1s (2 1s). Hence, the output is 1.
 * Input: arr[][] = [[0,0], [0,0]]
 * Output: -1
 * Explanation: No row contains any 1s, so the output is -1
 *
 *
 * Approach:
 * Since each row is sorted, start from the top-right corner of the matrix.
 * Move left if you see a '1' and down if you see a '0'.
 * Track the row index whenever you encounter more 1s than before.
 * This ensures O(n + m) time complexity, where n is the number of rows and m is the number of columns
 */
public class RowWithMaximumOnes2 {
    public int rowWithMax1s(int arr[][]) {
        int n = arr.length;
        if (n == 0) return -1;
        int m = arr[0].length;
        if (m == 0) return -1;

        int maxRowIndex = -1;       // Result: index of row with max 1s
        int col = m - 1;            // Start from last column (rightmost)

        // Traverse each row from top to bottom
        for (int row = 0; row < n; row++) {
            // Move left while there are 1s in current row
            while (col >= 0 && arr[row][col] == 1) {
                maxRowIndex = row;
                col--;
            }
            // If col < 0, this row is entirely 1s and can't be beaten
            if (col < 0) break;
        }
        return maxRowIndex;
    }

    // Example test
    public static void main(String[] args) {
        RowWithMaximumOnes2 solution = new RowWithMaximumOnes2();
        int[][] arr1 = {{0,1,1,1}, {0,0,1,1}, {1,1,1,1}, {0,0,0,0}};
        int[][] arr2 = {{0,0}, {1,1}};
        int[][] arr3 = {{0,0}, {0,0}};
        System.out.println(solution.rowWithMax1s(arr1)); // Output: 2
        System.out.println(solution.rowWithMax1s(arr2)); // Output: 1
        System.out.println(solution.rowWithMax1s(arr3)); // Output: -1
    }
}
