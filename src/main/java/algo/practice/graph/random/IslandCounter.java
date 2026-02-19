package algo.practice.graph.random;

/**
 *
 * Given a 2D grid grid where '1' represents land and '0' represents water, count and return the number of islands.
 * An island is formed by connecting adjacent lands horizontally or vertically and is surrounded by water.
 * You may assume water is surrounding the grid (i.e., all the edges are water).
 *
 * Input: grid = [
 *     ["0","1","1","1","0"],
 *     ["0","1","0","1","0"],
 *     ["1","1","0","0","0"],
 *     ["0","0","0","0","0"]
 *   ]
 * Output: 1
 *
 *
 * Input: grid = [
 *     ["1","1","0","0","1"],
 *     ["1","1","0","0","1"],
 *     ["0","0","1","0","0"],
 *     ["0","0","0","1","1"]
 *   ]
 * Output: 4
 *
 *
 * Note: Below solution use mxn space for storing visited.
 * Otherwise you can just mark the visited 1's as 0 to ensure we don't revisit them. This will avoid extra mxn space.
 *
 *
 *      * Another solution is direction base
 *      * private static final int[][] directions = {{1, 0}, {-1, 0},
 *      *                                                {0, 1}, {0, -1}};
 *      *
 *      * for (int[] dir : directions) {
 *      *   dfs(grid, r + dir[0], c + dir[1]);
 *      * }
 *      *
 *      *
 * Approach:
 * - Traverse each cell in the grid. When a '1' (land) is encountered, increment the island count and use DFS to mark all connected '1's as visited.
 * - DFS explores all four directions (up, down, left, right) and marks visited lands as '0' (water) to avoid revisiting.
 * - The process ensures each island is counted exactly once.
 * - Time complexity: O(m * n) for m rows and n columns; each cell is visited at most once.
 * - This approach avoids extra space for visited by modifying the input grid directly.
 */
public class IslandCounter {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int islands = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    dfs(grid, i, j);
                }
            }
        }

        return islands;
    }

    private void dfs(char[][] grid, int i, int j) {
        // Boundary checks and base case
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }

        // Mark as visited by changing '1' to '0'
        grid[i][j] = '0';

        // Explore all 4 directions
        dfs(grid, i - 1, j); // up
        dfs(grid, i + 1, j); // down
        dfs(grid, i, j - 1); // left
        dfs(grid, i, j + 1); // right
  }

    /*public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int[][] visited = new int[grid.length][grid[0].length];
        int islandCount = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j=0; j < grid[0].length; j++) {
                if(grid[i][j]=='1' && visited[i][j]==0) {
                    islandCount += 1;
                    dfs(i, j, grid, visited);
                }
            }
        }
        return islandCount;
    }

    void dfs(int i, int j, char[][] grid, int[][] visited){
        if(grid[i][j]=='1' && visited[i][j]==0) {
            visited[i][j] = 1;
            //check left
            if(j-1 >=0 && grid[i][j-1] == '1'){
                dfs(i, j-1, grid, visited);
            }
            //check right
            if(j+1 < grid[0].length && grid[i][j+1] == '1'){
                dfs(i, j+1, grid, visited);
            }
            //check up
            if(i-1 >=0  && grid[i-1][j] == '1') {
                dfs(i-1, j, grid, visited);
            }
            //checkdown
            if(i+1 < grid.length  && grid[i+1][j] == '1') {
                dfs(i+1, j, grid, visited);
            }

        }
    }*/
}
