package algo.practice.arrays;

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
 */
@Slf4j
public class BinarySearchInA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
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
        log.info("{}", new BinarySearchInA2DMatrix().searchMatrix(matrix, 16));
    }

}
