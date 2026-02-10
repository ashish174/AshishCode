package algo.practice.binarySearch;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * You are given an m x n 2-D integer array matrix and an integer target.
 *
 * Each row in matrix is sorted in non-decreasing order.
 * The first integer of every row is greater than the last integer of the previous row.
 * Return true if target exists within matrix or false otherwise
 *
 * Input: matrix = [[1,2,4,8],[10,11,12,13],[14,20,30,40]], target = 10
 * Output: true
 *
 * Logic:
 * We find the single row where the target could exist by comparing the target with the row's first and last elements.
 *  Find row mid, check if target is in between mid row first element and last element
 *  matrix[mid][0] <= target <= matrix[mid][COLUMN-1]
 *Once the correct row is found, we perform a normal binary search within that row to check if the target is present
 */
@Slf4j
public class BinarySearchInA2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        // Get the number of columns and rows in the matrix
        int COLUMN = matrix[0].length;
        int ROW = matrix.length;

        // Initialize variables to perform binary search on rows
        int l = 0;
        int r = ROW - 1;
        int rowToCheck = -1;

        // Check if the target is within the range of the matrix
        if (target < matrix[0][0] || target > matrix[ROW - 1][COLUMN - 1]) {
            // If the target is outside the range, it's not in the matrix
            return false;
        }

        // Perform binary search to find the row that may contain the target
        while (l <= r) {
            int mid = l + (r - l) / 2; // Avoid potential overflow
            // Check if the target is within the range of the current row
            if (matrix[mid][0] <= target && target <= matrix[mid][COLUMN - 1]) {
                // If the target is within the range, store the row index
                rowToCheck = mid;
                break;
            } else if (target < matrix[mid][0]) {
                // If the target is less than the first element of the current row, search in the upper half
                r = mid - 1;
            } else {
                // If the target is greater than the last element of the current row, search in the lower half
                l = mid + 1;
            }
        }

        // If no row is found that may contain the target, return false
        if (rowToCheck == -1) {
            return false;
        }

        // Perform binary search on the row that may contain the target
        int cl = 0;
        int cr = COLUMN - 1;
        while (cl <= cr) {
            int mid = cl + (cr - cl) / 2; // Avoid potential overflow
            if (matrix[rowToCheck][mid] == target) {
                // If the target is found, return true
                return true;
            } else if (matrix[rowToCheck][mid] > target) {
                // If the target is less than the current element, search in the left half
                cr = mid - 1;
            } else {
                // If the target is greater than the current element, search in the right half
                cl = mid + 1;
            }
        }

        // If the target is not found in the row, return false
        return false;
    }


    public boolean searchMatrixCopy(int[][] matrix, int target) {
        // find the row number
        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        int top = 0, bot = ROWS - 1;
        int row = -1;
        while (top <= bot) {
            row = (top + bot) / 2;
            if (target > matrix[row][COLS - 1]) {
                top = row + 1;
            } else if (target < matrix[row][0]) {
                bot = row - 1;
            } else {
                break;
            }
        }
        // find the item in the row
        if(row != -1) {
            int l = 0, r = COLS-1;
            while(l <= r) {
                int columnMid = (l+r)/2;
                if(matrix[row][columnMid] > target) {
                    r = columnMid - 1;
                } else if (matrix[row][columnMid] < target) {
                    l = columnMid + 1;
                } else {
                    return true; // matrix[mid][columnMid] == target
                }
            }
        }

        return false;
    }

    public static void main(String[] args){
        int[][] matrix = {
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        };
        log.info("{}", new BinarySearchInA2DMatrix().searchMatrixCopy(matrix, 16));
    }

}
