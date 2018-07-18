package designpattern.behavioural.statepattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TVStartState implements State{
  private static Logger logger = LogManager.getLogger(TVStartState.class);
  @Override
  public void doAction() {
    logger.info("TV is turned ON");
  }
}
