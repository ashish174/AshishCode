package games.snakeladder.service.models;

import javassist.bytecode.stackmap.TypeData.ClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Dice {
    private static Logger logger = LoggerFactory.getLogger(ClassName.class);
    private int MIN;
    private int MAX;
    Random random;

    public Dice(int MAX) {
        this.MIN = 1;
        this.MAX = MAX;
        random = new Random();
    }

    public Dice(int MIN, int MAX) {
        this.MIN = MIN;
        this.MAX = MAX;
        random = new Random();
    }

    int roll(){
        return this.MIN+random.nextInt(MAX-MIN+1);
    }
}
