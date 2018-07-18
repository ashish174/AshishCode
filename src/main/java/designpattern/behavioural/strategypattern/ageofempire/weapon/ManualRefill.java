package designpattern.behavioural.strategypattern.ageofempire.weapon;

public class ManualRefill implements Refillable {
  @Override
  public void refill() {
    System.out.println("Refilling Manually");
  }
}
