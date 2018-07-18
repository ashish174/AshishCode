package designpattern.behavioural.strategypattern.ageofempire.weapon;

public class NoRefill implements Refillable {
  @Override
  public void refill() {
    System.out.println("No Refill Required");
  }
}
