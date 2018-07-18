package designpattern.structural.decoratorpattern;

public class BasicCar implements Car{
  @Override
  public void assemble() {
    System.out.println("Adding Features of Basic Car");
  }

  @Override
  public int getCost() {
    return 100;
  }


}
