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
 */
public class RottingFruit {
    public int orangesRotting(int[][] grid) {
        if(grid == null || grid.length ==0){
            return -1;
        }
        int[][] directions = {{0,-1}, {0,1}, {-1, 0}, {1, 0}};
        Queue<int[]> queue = new LinkedList<>();
        int[][] minRottingTimeMatrix = new int[grid.length][grid[0].length];
        int minRottingTime = 0;
        int freshBananaCount = 0;
        // find all rotten banana and do multi BFS
        for(int i=0; i < grid.length; i++){
            for(int j=0; j < grid[0].length; j++){
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
                //If neighbour has banana
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
