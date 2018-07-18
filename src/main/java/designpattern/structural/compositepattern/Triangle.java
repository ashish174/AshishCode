package designpattern.structural.compositepattern;

public class Triangle implements Shape{
  @Override
  public void draw(String colourToUse) {
    System.out.println("Drawing Triangle with Colour "+colourToUse);
  }
}
