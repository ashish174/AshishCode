package algo.practice.graph.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find all cells from where if water is dropped, that water can flow to Pacific as well and Atlantic as well.
 * You are given a rectangular island heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 * The islands borders the Pacific Ocean from the top and left sides, and borders the Atlantic Ocean from the bottom and right sides.
 * Water can flow in four directions (up, down, left, or right) from a cell to a neighboring cell with height equal or lower. Water can also flow into the ocean from cells adjacent to the ocean.
 * Find all cells where water can flow from that cell to both the Pacific and Atlantic oceans. Return it as a 2D list where each element is a list [r, c] representing the row and column of the cell. You may return the answer in any order.
 *
 * Approach is to
 *  - start from cells which can reach to Pacific
 *  - Add new cells if neighbouring cells height >= current cell, so that water can flow from neighbouring cell to current cell
 *  - In the end you have all cells which are reachable to pacific
 *
 *  - Similarly start from cells which can reach to Atlantic
 *  - Add new cells if neighbouring cells height >= current cell, so that water can flow from neighbouring cell to current cell
 *  - In the end you have all cells which are reachable to atlantic
 *
 *  Now you find all cells which are common in both pacific and atlantic list.
 *
 * Approach:
 * - Start DFS (or BFS) from all cells on the Pacific edges (top and left) and mark all cells reachable by flowing "uphill" (neighbor height >= current).
 * - Do the same for the Atlantic edges (bottom and right).
 * - For each cell, if it is reachable from both oceans (marked in both visited arrays), water can flow to both.
 * - Collect and return all such cells as a list of coordinates.
 * - Time Complexity: O(m*n), with m rows and n columns.
 */
public class PacificAtlantic {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if(heights==null || heights.length==0){
            return null;
        }
        int ROWS = heights.length;
        int COLUMNS = heights[0].length;
        // Track cells reachable from Pacific and Atlantic oceans
        boolean[][] pacific = new boolean[ROWS][COLUMNS];
        boolean[][] atlantic = new boolean[ROWS][COLUMNS];
        // Start DFS from all cells bordering Pacific & atlantic (top row and bottom row)
        for(int c=0; c < COLUMNS; c++ ) {
            //Top row for pacific
            dfs(0, c, pacific, heights);
            //Bottom row for atlantic
            dfs(ROWS-1, c, atlantic, heights);
        }
        // Start DFS from all cells bordering Pacific & atlantic (first column and last column)
        for(int r=0; r < ROWS; r++ ) {
            //Left coulmn for pacific
            dfs(r, 0, pacific, heights);
            //Right column for atlantic
            dfs(r, COLUMNS-1, atlantic, heights);
        }
        // Find positions that can reach both oceans
        List<List<Integer>> finalList = new ArrayList<>();
        for(int r=0; r < ROWS; r++){
            for(int c=0; c < COLUMNS; c++) {
                if(pacific[r][c] && atlantic[r][c]) {
                    finalList.add(Arrays.asList(r,c));
                }
            }
        }
        return finalList;
    }

    /**
     * DFS traverses from the starting position, marking all cells reachable in the 'ocean' array.
     * It proceeds only to neighbors of equal or higher height -- this means water can "drop down"
     * from those cells to the origin cell, maintaining the reverse of the water flow for feasibility check.
     *
     * @param r        Start row index
     * @param c        Start column index
     * @param ocean    Boolean array for either Pacific or Atlantic reachability
     * @param heights  The height matrix
     */
    private void dfs(int r, int c, boolean[][] ocean, int[][] heights){
        // Directions: left, right, up, down
        int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        ocean[r][c] = true;
        for(int[] dir : directions){
            int nextRow = r + dir[0];
            int nextColumn = c + dir[1];
            // Check basic boundary condition(must be inside the grid)
            if(nextRow >= 0 && nextRow < heights.length
                    && nextColumn >=0 && nextColumn < heights[0].length
                    // check if neighbour is not already calculated
                    && !ocean[nextRow][nextColumn]
                    // check if neighbor is at more height than current cell,
                    // so that water can flow from neighbour -> current
                    && heights[nextRow][nextColumn]>=heights[r][c]){
                dfs(nextRow, nextColumn, ocean, heights);
            }
        }

    }

}
