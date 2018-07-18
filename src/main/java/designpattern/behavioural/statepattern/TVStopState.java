package designpattern.behavioural.statepattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TVStopState implements State{
  private static Logger logger = LogManager.getLogger(TVStopState.class);

  @Override
  public void doAction() {
    logger.info("TV is turned OFF");
  }
}
