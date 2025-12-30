package designpattern.behavioural.statepattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Represents the TV Muted state.
 */
public class TVMuteState implements State {
    private static Logger logger = LogManager.getLogger(TVMuteState.class);

    @Override
    public void handleRequest(TVContext context, String command) {
        switch (command) {
            case "unmute":
                logger.info("Unmuting the TV...");
                context.setState(new TVOnState());
                break;
            case "turnOff":
                logger.info("Turning OFF the TV from mute...");
                context.setState(new TVOffState());
                break;
            default:
                logger.info("TV is MUTED. Unsupported command in this state: " + command);
        }
    }

    @Override
    public String getStateName() {
        return "TV_MUTED";
    }
}
