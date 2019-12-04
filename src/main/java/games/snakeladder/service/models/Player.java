package games.snakeladder.service.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Player {
    public static Logger logger = LoggerFactory.getLogger(Player.class);
    private Cell position;
    private String name;

    void play(Dice dice){
        int numberRolled = dice.roll();
        Cell playerAt = getPosition();
        playerAt.moveForward(numberRolled);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cell getPosition() {
        return position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }
}
