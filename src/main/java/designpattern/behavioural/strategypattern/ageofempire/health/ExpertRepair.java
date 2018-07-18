package designpattern.behavioural.strategypattern.ageofempire.health;

public class ExpertRepair implements Repairable {
  @Override
  public void repair() {
    System.out.println("Repairing By Experts");
  }
}
