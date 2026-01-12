package codingRound.games.snakeladder.service.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Player {
    public static Logger logger = LoggerFactory.getLogger(Player.class);
    private ICell position;
    private String name;

    public Player(String name, ICell cell) {
        this.name = name;
        this.position = cell;
    }

    void play(Dice dice){
        int countFromTurn = getCountFromTurn(dice);
        ICell playerAt = getPosition();
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

    public ICell getPosition() {
        return position;
    }

    public void setPosition(ICell position) {
        this.position = position;
    }
}
