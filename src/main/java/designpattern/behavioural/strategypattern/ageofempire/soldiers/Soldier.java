package designpattern.behavioural.strategypattern.ageofempire.soldiers;

import designpattern.behavioural.strategypattern.ageofempire.weapon.Refillable;
import designpattern.behavioural.strategypattern.ageofempire.health.Repairable;

/**
 * Loose Coupling - Weapon & Health options are taken out through Composition
 * Any Strategy for Weapon and health can be used for diff Soldiers
 */
public abstract class Soldier {

  private Refillable refillable;
  private Repairable repairable;

  public Soldier(Refillable refillable, Repairable repairable) {
    this.refillable = refillable;
    this.repairable = repairable;
  }

  public abstract void attack();

  public abstract void gather(int x, int y);
//  void refill();
//  void repair();


  public Refillable getRefillable() {
    return refillable;
  }

  public Repairable getRepairable() {
    return repairable;
  }
}
