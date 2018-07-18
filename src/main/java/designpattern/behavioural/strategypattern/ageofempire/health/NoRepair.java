package designpattern.behavioural.strategypattern.ageofempire.health;

public class NoRepair implements Repairable {
  @Override
  public void repair() {
    System.out.println("No Repair required");
  }
}
