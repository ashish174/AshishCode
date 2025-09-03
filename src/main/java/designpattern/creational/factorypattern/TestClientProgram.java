package designpattern.creational.factorypattern;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class TestClientProgram {
  private static Logger logger = LogManager.getLogger(TestClientProgram.class);
  public static void main(String[] args) {
    Computer computer1 =  ComputerFactory.getComputer("PC", "4GB", "120GB", "4GHz");
    Computer computer2 = ComputerFactory.getComputer("Server", "10GB", "200GB", "10GHz");

    logger.info("Computer created is : "+computer1);
    logger.info("Ram is : "+computer1.getRAM());
    logger.info("Computer created is : "+computer2);
    logger.info("Ram is : "+computer2.getRAM());
  }
}
