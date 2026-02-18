package algo.practice.graph.basic;

import java.util.LinkedList;
import java.util.Queue;

import algo.practice.graph.coregraphclasses.DirectedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Given a snake and ladder board, find the minimum number of dice throws required to
 * reach the destination or last cell from the source or 1st cell.
 * Basically, the player has total control over the outcome of the dice throw.
 *
 * This uses Breadth-First Search (BFS) to check all possible outcomes at every next step for all dice throws from 1-6.
 * At every step, it enqueue all current cell, then check next step until it reaches last cell.
 *
 */
public class SnakeLadderPrblm {
    public static final Logger LOGGER = LoggerFactory.getLogger(SnakeLadderPrblm.class);

    /**
     * Calculates the minimum number of dice throws required to reach the last cell.
     *
     * @param boardSize   Number of cells on the board
     * @param cellMapping Array of size boardSize. If there's a snake or ladder at i,
     *                    cellMapping[i] gives the destination cell. A value of 0 means normal cell.
     * @return            Minimum number of dice throws to reach the end
     */
    public static int findMinDiceThrows(int boardSize, int[] cellMapping){
        // visited[i]=true if cell i is already visited
        boolean[] visited = new boolean[boardSize];

        // Queue for BFS: stores PlayerMove states
        Queue<PlayerMove> queue = new LinkedList<>();

        // Begin the game from cell 0 with 0 dice throws
        PlayerMove startingMove = new PlayerMove(0, 0);
        visited[0] = true;
        queue.add(startingMove);

        PlayerMove currentMove = null;

        while (!queue.isEmpty()) {
            currentMove = queue.poll();

            // Stop BFS if we've reached the last cell
            if (currentMove.cellIndex == boardSize - 1) {
                break;
            }

            // Try moving from current cell with a dice roll of 1 to 6
            for (int diceRoll = 1; diceRoll <= 6; diceRoll++) {
                int nextCell = currentMove.cellIndex + diceRoll;
                if (nextCell >= boardSize) break; // Can't go past board

                if (!visited[nextCell]) {
                    visited[nextCell] = true;
                    //destination cell for given diceroll value factoring in snake/ladder
                    int destination = (cellMapping[nextCell] != 0)
                            ? cellMapping[nextCell]
                            : nextCell;

                    // Add the resulting cell and increment dice throw count
                    PlayerMove move = new PlayerMove(destination, currentMove.diceThrows + 1);
                    // Add to queue
                    queue.add(move);
                }
            }
        }

        // If BFS finished and last cell was reached, return dice throws
        return (currentMove != null) ? currentMove.diceThrows : -1;
    }

    /**
     * Helper class to store player's position and the number of dice throws taken so far.
     */
    private static class PlayerMove {
        int cellIndex;
        int diceThrows;

        public PlayerMove(int cellIndex, int diceThrows) {
            this.cellIndex = cellIndex;
            this.diceThrows = diceThrows;
        }
    }

    public static void main(String[] args) {
        int boardSize = 30;
        int[] cellMapping = new int[boardSize];

        // Ladders: eg. ladder from cell 2 to 21
        cellMapping[2] = 21;
        cellMapping[4] = 7;
        cellMapping[10] = 25;
        cellMapping[19] = 28;

        // Snakes: eg. snake from cell 26 to 1
        cellMapping[26] = 1;
        cellMapping[20] = 8;
        cellMapping[16] = 3;
        cellMapping[18] = 6;

        int minThrows = findMinDiceThrows(boardSize, cellMapping);

        LOGGER.info("Minimum number of dice throws needed to reach from start (cell 1) to end (cell {}) is {}",
                boardSize, minThrows);
    }
}