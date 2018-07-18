package designpattern.structural.decoratorpattern;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class TestClientProgram {
  private static Logger logger = LogManager.getLogger(TestClientProgram.class);
  public static void main(String[] args) {
    Car sportsCar = new SportsCarDecorator(new BasicCar());
    sportsCar.assemble();
    System.out.println("sportsCar Cost : "+sportsCar.getCost());


    Car sportsLuxuryCar = new SportsCarDecorator(new LuxuryCarDecorator(new BasicCar()));
    sportsLuxuryCar.assemble();
    System.out.println("sportsLuxuryCar Cost : "+sportsLuxuryCar.getCost());


  }
}
