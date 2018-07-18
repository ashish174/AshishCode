package designpattern.structural.bridgepattern;

public class Triangle extends Shape{

  public Triangle(Color color) {
    super(color);
  }

  @Override
  public void applyColor() {
    System.out.print(" Triangle filled with Color ");
    color.applyColor();
  }
}
