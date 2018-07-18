package designpattern.structural.bridgepattern;


import designpattern.structural.facadepattern.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;



public class TestClientProgram {
  private static Logger logger = LogManager.getLogger(TestClientProgram.class);
  public static void main(String[] args) {
    Shape triangle = new Triangle(new RedColor());
    triangle.applyColor();

    Shape pentagon = new Pentagon(new GreenColor());
    pentagon.applyColor();

  }
}
