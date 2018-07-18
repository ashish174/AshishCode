package designpattern.behavioural.strategypattern.ageofempire.health;

public class SelfRepair implements Repairable {
  @Override
  public void repair() {
    System.out.println("Repairing Self");
  }
}
