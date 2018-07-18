package designpattern.structural.proxypattern.atm;

public class ATMMachineImpl implements ATMMachine {
  long totalCash;
  @Override
  public void addCashToATM(long amount) {
    totalCash = totalCash+amount;
  }

  @Override
  public long getAccountBalance() {
    return 0;
  }

  @Override
  public void withDrawFromAccount(long amount) {

  }
}
