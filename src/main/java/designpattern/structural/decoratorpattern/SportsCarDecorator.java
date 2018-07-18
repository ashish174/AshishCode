package designpattern.structural.decoratorpattern;

public class SportsCarDecorator extends CarDecorator{

  public SportsCarDecorator(Car car) {
    super(car);
  }

  @Override
  public void assemble() {
    super.assemble();
    System.out.println("Adding Features of Sports Car ");
  }

  @Override
  public int getCost() {
    return super.getCost() + 300;
  }
}
