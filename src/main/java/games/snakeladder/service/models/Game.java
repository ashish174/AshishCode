package games.snakeladder.service.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Game {
    public static Logger logger = LoggerFactory.getLogger(Game.class);
    private int size;
    private Dice dice;
    private boolean isGameOver;
    private Player winner;
    private Queue<Player> playerTurn;
    private Map<Integer, Integer> ladders;
    private Map<Integer, Integer> snakes;


    public Game(int size, List<Player> players) {
        this.size = size;
        this.dice = new Dice(6);
        playerTurn = new LinkedList<>(players);
        configureBoard();

        //add ladders
        //add snakes
    }

    public Game(int size, List<Player> players, Map<Integer, Integer> ladders, Map<Integer, Integer> snakes) {
        this.size = size;
        this.dice = new Dice(6);
        playerTurn = new LinkedList<>(players);
        configureBoard(ladders, snakes);
        //add ladders
        //add snakes
    }

    public void startGame(){
        //Play Game
        while(isGameOver){
        Player turnOf = playerTurn.remove();
        logger.info("Player {} Turn at Position {}", turnOf.getName(), turnOf.getPosition());
        turnOf.play(dice);
        playerTurn.add(turnOf);
        }
        logger.info("Winner is {}",getWinner());
        //Printing Game End Status
        logger.info("Game Stats : ");
        while (!playerTurn.isEmpty()){
            Player player = playerTurn.remove();
            logger.info("Player {} - {}", player.getName(), player.getPosition());
        }
    }

    private Player getWinner(){
        return isGameOver ?winner:null;
    }

    private void configureBoard() {
        Map<Integer, Integer> ladders = new HashMap<>();
        Map<Integer, Integer> snakes = new HashMap<>();
        ladders.put(3, 10);
        ladders.put(8, 18);
        ladders.put(15, 5);
        ladders.put(25, 10);

        snakes.put(20, 10);
        snakes.put(27, 7);
        snakes.put(40, 8);
        snakes.put(44, 20);
        snakes.put(48, 25);

        configureBoard(ladders, snakes);

    }

    private void configureBoard(Map<Integer, Integer> ladders, Map<Integer, Integer> snakes) {
        //validate ladders
        //validate snakes
        this.ladders = ladders;
        this.snakes = snakes;
    }
}
