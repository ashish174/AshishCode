package designpattern.behavioural.statepattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Represents the TV OFF state.
 */
public class TVOffState implements State {
    private static Logger logger = LogManager.getLogger(TVOffState.class);

    @Override
    public void handleRequest(TVContext context, String command) {
        switch (command) {
            case "turnOn":
                logger.info("Turning ON the TV...");
                context.setState(new TVOnState());
                break;
            default:
                logger.info("TV is OFF. Please turn it ON first. Unsupported command: " + command);
        }
    }

    @Override
    public String getStateName() {
        return "TV_OFF";
    }
}