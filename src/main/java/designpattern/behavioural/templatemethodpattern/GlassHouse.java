package designpattern.behavioural.templatemethodpattern;

public class GlassHouse extends HouseTemplate {
  @Override
  public void buildPillars() {
    System.out.println("Build Glass Pillars");
  }

  @Override
  public void buildWalls() {
    System.out.println("Build Glass Walls");
  }
}
