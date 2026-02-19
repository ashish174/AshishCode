package algo.practice.graph.random;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a 2-D matrix grid. Each cell can have one of three possible values:
 *
 * 0 representing an empty cell
 * 1 representing a fresh fruit
 * 2 representing a rotten fruit
 * Every minute, if a fresh fruit is horizontally or vertically adjacent to a rotten fruit, then the fresh fruit also becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until there are zero fresh fruits remaining. If this state is impossible within the grid, return -1.
 *
 * Input: grid = [[1,1,0],[0,1,1],[0,1,2]]
 *
 * Output: 4
 *
 * Input: grid = [[1,0,1],[0,2,0],[1,0,1]]
 *
 * Output: -1
 *
 * Approach:
 * - Use multi-source BFS: add all initial rotten fruits (value 2) to a queue as starting points.
 * - Each minute, propagate the rotting process to all adjacent fresh fruits (value 1), marking them rotten and updating their rotting time.
 * - Track the number of fresh fruits; decrement the count each time a fresh fruit rots.
 * - After BFS completes, if any fresh fruit remains, return -1. Otherwise, return the maximum rotting time found.
 * - Time Complexity: O(m * n), as each cell is visited at most once.
 */
public class RottingFruit {
    public int orangesRotting(int[][] grid) {
        if(grid == null || grid.length ==0){
            return -1;
        }
        int[][] directions = {{0,-1}, {0,1}, {-1, 0}, {1, 0}};
        Queue<int[]> queue = new LinkedList<>();
        // minRottingTimeMatrix[i][j] records the minute at which cell (i, j) turned rotten.
        // It helps track the spread of rot to calculate the total time, ensuring we don't revisit and reprocess a cell
        int[][] minRottingTimeMatrix = new int[grid.length][grid[0].length];
        int minRottingTime = 0;
        int freshBananaCount = 0;
        // find all rotten banana and do multi BFS
        for(int i=0; i < grid.length; i++){
            for(int j=0; j < grid[0].length; j++){
                //add rotten fruit to queue
                if(grid[i][j]==2){
                    queue.add(new int[]{i,j});
                }
                //count fresh banana as well
                if(grid[i][j]==1){
                    freshBananaCount++;
                }
            }
        }
        while(!queue.isEmpty()){
            int[] cell = queue.remove();
            int r = cell[0];
            int c = cell[1];

            // check all directions
            for(int[] dir : directions){
                int nextRow = r + dir[0];
                int nextColumn = c + dir[1];
                //check boundaries
                if(nextRow < 0 || nextRow >= grid.length || nextColumn < 0 || nextColumn >= grid[0].length){
                    continue;
                }
                // Skip for anything other than banana
                if(grid[nextRow][nextColumn] != 1) {
                    continue;
                }
                //If neighbour has banana & is not processed (minRottingTimeMatrix[][] = 0)
                if(grid[nextRow][nextColumn]==1 && minRottingTimeMatrix[nextRow][nextColumn] == 0) {
                    //convert fresh banana to rotten
                    grid[nextRow][nextColumn] = 2;
                    //decrease freshbanana count
                    freshBananaCount--;
                    minRottingTimeMatrix[nextRow][nextColumn] = minRottingTimeMatrix[r][c] + 1;
                    minRottingTime = Integer.max(minRottingTime, minRottingTimeMatrix[nextRow][nextColumn]);
                    queue.add(new int[]{nextRow, nextColumn});
                }
            }

        }
        // Check if there is any good banana left, then return -1, else return minRottingTime
        return freshBananaCount > 0 ? -1 : minRottingTime;
    }

}
