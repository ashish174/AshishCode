package designpattern.behavioural.statepattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Context class representing the TV, which has a current State.
 * Handles commands and delegates to the current state's logic.
 */
public class TVContext {
  private static Logger logger = LogManager.getLogger(TVContext.class);
  private State currentState;

  public TVContext() {
    // TV starts as OFF by default
    this.currentState = new TVOffState();
  }

  public State getCurrentState() {
    return currentState;
  }

  /**
   * Sets the state of the TV.
   * @param newState State to transition to.
   */
  public void setState(State newState) {
    logger.info("Changing state to: " + newState.getStateName());
    this.currentState = newState;
  }

  /**
   * Handles a user command, delegates to the current state's handler.
   * @param command e.g. "turnOn", "turnOff", "mute", "unmute"
   */
  public void handleCommand(String command) {
    currentState.handleRequest(this, command);
  }
}
