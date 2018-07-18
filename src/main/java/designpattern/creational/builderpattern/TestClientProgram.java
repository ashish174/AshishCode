package designpattern.creational.builderpattern;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class TestClientProgram {
  private static Logger logger = LogManager.getLogger(TestClientProgram.class);
  public static void main(String[] args) {
    Computer myComputer = new Computer.ComputerBuilder("500GB", "4GB")
        .setGraphicsCardEnabled(true)
        //.setBluetoothEnabled(false)
        .build();

    logger.info("Computer created is : "+myComputer);

  }
}
