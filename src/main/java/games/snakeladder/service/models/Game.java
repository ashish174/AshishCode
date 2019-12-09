package games.snakeladder.service.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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

        //add ladders
        //add snakes
    }

    public void startGame(){
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
}
