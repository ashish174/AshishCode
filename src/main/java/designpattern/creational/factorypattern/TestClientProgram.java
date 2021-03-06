package designpattern.creational.factorypattern;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class TestClientProgram {
  private static Logger logger = LogManager.getLogger(TestClientProgram.class);
  public static void main(String[] args) {
    Computer myPC =  ComputerFactory.getComputer("PC", "4GB", "120GB", "4GHz");
    Computer myServer = ComputerFactory.getComputer("Server", "10GB", "200GB", "10GHz");

    logger.info("PC created is ?"+myPC);
    logger.info("Server created is ?"+myServer);

  }
}
