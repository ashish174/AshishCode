package designpattern.behavioural.strategypattern.ageofempire;

import designpattern.behavioural.strategypattern.ageofempire.health.ExpertRepair;
import designpattern.behavioural.strategypattern.ageofempire.health.SelfRepair;
import designpattern.behavioural.strategypattern.ageofempire.soldiers.Archer;
import designpattern.behavioural.strategypattern.ageofempire.soldiers.Gunman;
import designpattern.behavioural.strategypattern.ageofempire.soldiers.Soldier;
import designpattern.behavioural.strategypattern.ageofempire.soldiers.SwordsMan;
import designpattern.behavioural.strategypattern.ageofempire.weapon.AutomaticRefill;
import designpattern.behavioural.strategypattern.ageofempire.weapon.ManualRefill;
import designpattern.behavioural.strategypattern.ageofempire.weapon.NoRefill;

public class TestSoldier {
  public static void main(String[] args) {
    Soldier archer = new Archer.ArcherBuilder()
        .setRepairable(new SelfRepair())
        .setRefillable(new ManualRefill())
        .createArcher();
    System.out.println("Archer: ");
    archer.getRefillable().refill();
    archer.getRepairable().repair();

    Soldier gunFighter = new Gunman(new AutomaticRefill(), new ExpertRepair());
    System.out.println("Gunman: ");
    gunFighter.getRepairable().repair();
    gunFighter.getRefillable().refill();

    Soldier swordMan = new SwordsMan(new NoRefill(), new SelfRepair());
    System.out.println("SwordMan: ");
    swordMan.getRepairable().repair();
    swordMan.getRefillable().refill();
  }
}
