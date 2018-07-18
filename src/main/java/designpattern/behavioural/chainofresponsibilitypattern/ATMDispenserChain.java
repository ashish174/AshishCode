package designpattern.behavioural.chainofresponsibilitypattern;


import designpattern.behavioural.templatemethodpattern.GlassHouse;
import designpattern.behavioural.templatemethodpattern.HouseTemplate;
import designpattern.behavioural.templatemethodpattern.WoodenHouse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Scanner;


public class ATMDispenserChain {
  private DispenseChain c1;
  private static Logger logger = LogManager.getLogger(ATMDispenserChain.class);

  public ATMDispenserChain() {
    this.c1 = new Dollar50Dispenser();
    DispenseChain c2 = new Dollar20Dispenser();
    DispenseChain c3 = new Dollar10Dispenser();
    c1.setNextChain(c2);
    c2.setNextChain(c3);
  }

  public static void main(String[] args) {
    ATMDispenserChain atmDispenserChain = new ATMDispenserChain();
    while(true){
      int amount;
      System.out.println("Enter Amount to Dispense ");
      Scanner input = new Scanner(System.in);
      amount = input.nextInt();
      if(amount%10!=0){
        System.out.println("Amount need to be in Multiple of 10");
        return;
      }
      atmDispenserChain.c1.dispense(new Currency(amount));

    }

  }
}
