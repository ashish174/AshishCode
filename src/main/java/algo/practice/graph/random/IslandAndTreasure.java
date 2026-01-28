package algo.practice.graph.random;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a
 * m×n 2D grid initialized with these three possible values:
 *
 * -1 - A water cell that can not be traversed.
 * 0 - A treasure chest.
 * INF - A land cell that can be traversed. We use the integer 2^31 - 1 = 2147483647 to represent INF.
 * Fill each land cell with the distance to its nearest treasure chest. If a land cell cannot reach a treasure chest then the value should remain INF.
 *
 * Assume the grid can only be traversed up, down, left, or right.
 *
 * Modify the grid in-place.
 *
 *
 *Example 1:
 *
 * Input: [
 *   [2147483647,-1,0,2147483647],
 *   [2147483647,2147483647,2147483647,-1],
 *   [2147483647,-1,2147483647,-1],
 *   [0,-1,2147483647,2147483647]
 * ]
 *
 * Output: [
 *   [3,-1,0,1],
 *   [2,2,1,-1],
 *   [1,-1,2,-1],
 *   [0,-1,3,4]
 * ]
 *
 *
 * Multi-BFS Approach:
 * If we start the queue with all treasures, the first time the wave reaches an empty cell, it must be
 * from the closest treasure (because BFS guarantees the first visit is the shortest distance in an unweighted grid).
 * So each cell gets filled with its minimum distance to any treasure.
 *
 *
 */
public class IslandAndTreasure {
    private int[][] directions = {{-1,0}, {1, 0}, {0, -1}, {0, 1}};
    private int INF = 2147483647;
    private int rows, columns;

    public void islandsAndTreasure(int[][] grid) {
        if(grid==null || grid.length==0){
            return;
        }
        rows = grid.length;
        columns = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        // Add all treasure cells (0) to the queue
        // These are our BFS starting points (multi-source)
        for(int r=0; r < rows; r++){
            for(int c=0; c < columns; c++){
                if(grid[r][c]==0){
                    queue.add(new int[] {r, c});
                }
            }
        }
        if(queue.isEmpty()){
            return;
        }
        while(!queue.isEmpty()){
            int[] cell = queue.poll();
            int row = cell[0];
            int column = cell[1];

            // Explore all 4 neighbors
            for(int[] dir : directions){
                int nextRow = row + dir[0];
                int nextColumn = column + dir[1];
                //Boundary check
                if(nextRow<0 || nextRow >=rows || nextColumn <0 || nextColumn >= columns){
                    continue;
                }
                // Only update land cells that are still INF
                // If it's water (-1) or already has a shorter distance, skip
                if(grid[nextRow][nextColumn] != INF) {
                    continue;
                }
                //Update island with INF distance to shortest distance
                grid[nextRow][nextColumn] = grid[row][column] + 1;
                //Add neighbor/island to queue to continue BFS
                queue.add(new int[] {nextRow, nextColumn});
            }
        }


    }
}
