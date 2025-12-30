package designpattern.behavioural.statepattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Represents the TV ON state.
 */
public class TVOnState implements State {
    private static Logger logger = LogManager.getLogger(TVOnState.class);

    @Override
    public void handleRequest(TVContext context, String command) {
        switch (command) {
            case "turnOff":
                logger.info("Turning OFF the TV...");
                context.setState(new TVOffState());
                break;
            case "mute":
                logger.info("Muting the TV...");
                context.setState(new TVMuteState());
                break;
            default:
                logger.info("TV is already ON. Unsupported command in this state: " + command);
        }
    }

    @Override
    public String getStateName() {
        return "TV_ON";
    }
}
