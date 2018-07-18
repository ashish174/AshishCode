package designpattern.structural.bridgepattern;

public class Pentagon extends Shape{

  public Pentagon(Color color) {
    super(color);
  }

  @Override
  public void applyColor() {
    System.out.print(" Pentagon filled with Color ");
    color.applyColor();
  }
}
