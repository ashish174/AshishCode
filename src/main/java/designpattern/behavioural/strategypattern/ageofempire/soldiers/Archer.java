package designpattern.behavioural.strategypattern.ageofempire.soldiers;

import designpattern.behavioural.strategypattern.ageofempire.health.Repairable;
import designpattern.behavioural.strategypattern.ageofempire.weapon.Refillable;

public class Archer extends Soldier {
  public Archer(Refillable refillable, Repairable repairable) {
    super(refillable, repairable);
  }

  public Archer(ArcherBuilder archerBuilder) {
    super(archerBuilder.refillable, archerBuilder.repairable);
  }

  @Override
  public void attack() {

  }

  @Override
  public void gather(int x, int y) {

  }

  public static class ArcherBuilder {
    private Refillable refillable;
    private Repairable repairable;

    public ArcherBuilder setRefillable(Refillable refillable) {
      this.refillable = refillable;
      return this;
    }

    public ArcherBuilder setRepairable(Repairable repairable) {
      this.repairable = repairable;
      return this;
    }

    public Archer createArcher() {
      return new Archer(this);
    }
  }
}
