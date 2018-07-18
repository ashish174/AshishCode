package designpattern.behavioural.strategypattern.ageofempire.soldiers;

import designpattern.behavioural.strategypattern.ageofempire.health.Repairable;
import designpattern.behavioural.strategypattern.ageofempire.weapon.Refillable;

public class Gunman extends Soldier {
  public Gunman(Refillable refillable, Repairable repairable) {
    super(refillable, repairable);
  }

  @Override
  public void attack() {

  }

  @Override
  public void gather(int x, int y) {

  }

/*
  @Override
  public void refill() {

  }

  @Override
  public void repair() {

  }*/
}
