package games.snakeladder.service.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Player {
    public static Logger logger = LoggerFactory.getLogger(Player.class);
    private Cell position;
    private String name;

    void play(Dice dice){
        int countFromTurn = getCountFromTurn(dice);
        Cell playerAt = getPosition();
        playerAt.moveForward(countFromTurn);
    }

    int getCountFromTurn(Dice dice){
        int stepCount = 0;
        int num_of_attempt = 0;
        int number;
        do{
            if(num_of_attempt==3){
                logger.info("Got 3 Sixes. Restarting Process");
                stepCount = 0;
                num_of_attempt=0;
            }
            number = dice.roll();
            stepCount+=number;
            num_of_attempt++;

        } while (number==6);
        return stepCount;
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
