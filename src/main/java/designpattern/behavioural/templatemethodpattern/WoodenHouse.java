package designpattern.behavioural.templatemethodpattern;

public class WoodenHouse extends HouseTemplate {
  @Override
  public void buildPillars() {
    System.out.println("Build Wooden Pillars");
  }

  @Override
  public void buildWalls() {
    System.out.println("Build Wooden Walls");
  }
}
