package designpattern.structural.proxypattern.atm;

public class ATMTest {
  public static void main(String[] args) {
    ATMMachine atmMachineClient = new ATMMachineClientProxy();

    ATMMachine atmMachineClient1 = new ATMMachineClientProxy();
    atmMachineClient1.addCashToATM(100);
  }

}
