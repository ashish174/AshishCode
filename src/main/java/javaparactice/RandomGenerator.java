package javaparactice;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomGenerator.class);
    public static void main(String[] args) {
        int random1 = new Random().nextInt(10);
        LOGGER.info("Random Generated number between 0 - 9 : {}", random1);
        // Math.random() will give 0-1 value
        int random2 = (int) (Math.random()*10);
        LOGGER.info("Random Generated number between 0 - 9 : {}", random2);

    }
}
