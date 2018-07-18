package designpattern.behavioural.strategypattern.ageofempire.weapon;

public class AutomaticRefill implements Refillable {
  @Override
  public void refill() {
    System.out.println("Refilling Automatically");
  }
}
