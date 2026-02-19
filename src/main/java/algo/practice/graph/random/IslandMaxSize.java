package algo.practice.graph.random;

/**
 * You are given a matrix grid where grid[i] is either a 0 (representing water) or 1 (representing land).
 *
 * An island is defined as a group of 1's connected horizontally or vertically. You may assume all four edges of the grid are surrounded by water.
 *
 * The area of an island is defined as the number of cells within the island.
 *
 * Return the maximum area of an island in grid. If no island exists, return 0.
 *
 * Approach:
 * - Traverse each cell in the grid. When a cell with value '1' (land) is found, use DFS to explore all connected land cells.
 * - During each DFS, count the size (area) of the current island by incrementing a counter for each land cell visited.
 * - Mark cells as visited by setting them to '0' to avoid counting them again.
 * - Track and update the maximum island area found throughout the traversal.
 * - Return the largest area (maximum island size) found.
 * - Time Complexity: O(m*n), visiting each cell at most once.
 */
public class IslandMaxSize {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int islandCount = 0;
        int islandMaxSize = 0;
        int[] islandSize = new int[1];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, islandSize);
                    islandCount++;
                    islandMaxSize = Integer.max(islandMaxSize, islandSize[0]);
                    //reset islandSize
                    islandSize[0] = 0;
                }
            }
        }
        return islandMaxSize;
    }

    private void dfs(int[][] grid, int i, int j, int[] islandSize) {
        // Boundary checks and base case
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return;
        }

        // Mark as visited by changing '1' to '0'
        grid[i][j] = 0;
        islandSize[0] += 1;


        // Explore all 4 directions
        dfs(grid, i - 1, j, islandSize); // up
        dfs(grid, i + 1, j, islandSize); // down
        dfs(grid, i, j - 1, islandSize); // left
        dfs(grid, i, j + 1, islandSize); // right
    }
}
