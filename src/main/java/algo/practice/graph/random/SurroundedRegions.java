package algo.practice.graph.random;

/**
 * You are given a 2-D matrix board containing 'X' and 'O' characters.
 *
 * If a continous, four-directionally connected group of 'O's is surrounded by 'X's, it is considered to be surrounded.
 *
 * Change all surrounded regions of 'O's to 'X's and do so in-place by modifying the input board.
 *
 * Input: board = [
 *   ["X","X","X","X"],
 *   ["X","O","O","X"],
 *   ["X","O","O","X"],
 *   ["X","X","X","O"]
 * ]
 *
 * Output: [
 *   ["X","X","X","X"],
 *   ["X","X","X","X"],
 *   ["X","X","X","X"],
 *   ["X","X","X","O"]
 * ]
 *
 * Idea is to find all Os at the edges, and find all Os reachable for edge Os.
 * Instead of using extra space edgeReachable[][], you can mark edgeReachable 0s as #,
 * and all remaining 0s can be converted into X.
 *
 */
public class SurroundedRegions {
    public void solve(char[][] board) {
        if(board==null || board.length==0){
            return;
        }
        int ROWS = board.length;
        int COLUMNS = board[0].length;
        boolean[][] edgeReachable = new boolean[ROWS][COLUMNS];
        // find Os at first and last column
        for(int r=0; r < ROWS; r++) {
            if(board[r][0]=='O'){
                //if edge O is found, find all connected Os
                dfs(r, 0, board, edgeReachable);
            }
            if(board[r][COLUMNS-1]=='O'){
                //if edge O is found, find all connected Os
                dfs(r, COLUMNS-1, board, edgeReachable);
            }
        }

        // find Os at first and last row
        for(int c=0; c < COLUMNS; c++) {
            if(board[0][c]=='O'){
                //if edge O is found, find all connected Os
                dfs(0, c, board, edgeReachable);
            }
            if(board[ROWS-1][c]=='O'){
                //if edge O is found, find all connected Os
                dfs(ROWS-1, c, board, edgeReachable);
            }
        }


        for(int r=0; r<ROWS; r++){
            for(int c=0; c < COLUMNS; c++){
                //All Os not edge connected, can be converted into X
                if(board[r][c]=='O' && !edgeReachable[r][c]){
                    board[r][c] = 'X';
                }
            }
        }

    }

    // dfs to find all 0s reachable from edge 0s
    void dfs(int r, int c,  char[][] board, boolean[][] edgeReachable){
        edgeReachable[r][c] = true;
        int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        for(int[] dir : directions) {
            int nextRow = r + dir[0];
            int nextColumn = c + dir[1];
            // check boundaries
            if(nextRow<0 || nextRow >= board.length || nextColumn <0 || nextColumn >= board[0].length){
                continue;
            }
            //if neighbour has O and is not already visited
            if(board[nextRow][nextColumn]=='O' && !edgeReachable[nextRow][nextColumn]){
                dfs(nextRow, nextColumn, board, edgeReachable);
            }
        }

    }
}
