package designpattern.structural.compositepattern;

public class Circle implements Shape{
  @Override
  public void draw(String colourToUse) {
    System.out.println("Drawing Circle with Colour "+colourToUse);
  }
}
