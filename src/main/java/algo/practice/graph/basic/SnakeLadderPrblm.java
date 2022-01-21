package algo.practice.graph.basic;

import java.util.LinkedList;
import java.util.Queue;

import algo.practice.graph.DirectedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnakeLadderPrblm {
    public static final Logger LOGGER = LoggerFactory.getLogger(SnakeLadderPrblm.class);

    public static int minStepToReachSnakeLadderTop(int n, int[] move){
        int[] visited = new int[n];
        Queue<Cell> queue = new LinkedList<>();
        Cell startCell = new Cell(0, 0);
        visited[startCell.position] = 1;
        queue.add(startCell);
        Cell currentCell = new Cell();
        while (!queue.isEmpty()){
            currentCell = queue.poll();
            if(currentCell.position==n-1){
                break;
            }
            int currPosition = currentCell.position;
            for(int j = currPosition+1 ; j <= currPosition+6 && j < n; j++ ){
                //So that visited node should not be queue again.
                if(visited[j]==0){
                    Cell cell = new Cell();
                    cell.dist = currentCell.dist+1;
                    visited[j] = 1;
                    if(move[j]!=0){
                        //Encountered a snake/ladder
                        cell.position = move[j];
                    } else {
                        cell.position = j;
                    }
                    queue.add(cell);
                }
            }
        }
        return currentCell.dist;
    }

    public static class Cell{
        int position;
        int dist;

        public Cell() {
        }

        public Cell(int position, int dist) {
            this.position = position;
            this.dist = dist;
        }
    }

    public static void main(String[] args) {
        int n = 30;
        int[] moves = new int[n];
        DirectedGraph directedGraph = new DirectedGraph(n);
        for(int i = 0; i < n ; i++){

        }
        // Ladders
        moves[2] = 21;
        moves[4] = 7;
        moves[10] = 25;
        moves[19] = 28;

        // Snakes
        moves[26] = 1;
        moves[20] = 8;
        moves[16] = 3;
        moves[18] = 6;
        LOGGER.info("Minimum number to throws needed to reach from {} to  top {} of Snake Ladder Game is {}",
                1, n, minStepToReachSnakeLadderTop(n, moves));
    }
}
