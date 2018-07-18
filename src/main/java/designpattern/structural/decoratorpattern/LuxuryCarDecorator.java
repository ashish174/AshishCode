package designpattern.structural.decoratorpattern;

public class LuxuryCarDecorator extends CarDecorator{

  public LuxuryCarDecorator(Car car) {
    super(car);
  }

  @Override
  public void assemble() {
    super.assemble();
    System.out.println("Adding Features of Luxury Car ");
  }

  @Override
  public int getCost() {
    return super.getCost()+200;
  }
}
