package designpattern.structural.proxypattern.atm;

public interface ATMMachine {
  public void addCashToATM(long amount);
  public long getAccountBalance();
  public void withDrawFromAccount(long amount);


}
