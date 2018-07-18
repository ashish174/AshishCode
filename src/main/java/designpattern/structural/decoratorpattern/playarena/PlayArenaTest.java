package designpattern.structural.decoratorpattern.playarena;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayArenaTest {

  private static Logger logger = LoggerFactory.getLogger(PlayArenaTest.class);

  public static void main(String[] args) {
    CourtBooking courtBooking = new BallPackDecorator(new RacketDecorator(new GrassCourt()));
    logger.info("Item Booked Info {}", courtBooking.getInfo());
    logger.info("Item Booked Cost {}", courtBooking.getCost());
  }
}
