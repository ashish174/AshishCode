package designpattern.behavioural.templatemethodpattern;


import designpattern.structural.proxypattern.CommandExecutor;
import designpattern.structural.proxypattern.CommandExecutorProxy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class TestClientProgram {
  private static Logger logger = LogManager.getLogger(TestClientProgram.class);
  public static void main(String[] args) {
    HouseTemplate woodHouse = new WoodenHouse();
    woodHouse.buildHouse();

    HouseTemplate glassHouse = new GlassHouse();
    glassHouse.buildHouse();


  }
}
