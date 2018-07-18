package designpattern.structural.proxypattern.atm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ATMMachineClientProxy implements ATMMachine {

  private Logger logger = LoggerFactory.getLogger(ATMMachineClientProxy.class);
  private boolean isAdmin = false;
  private double totalCash;
  private ATMMachineImpl atmMachine = new ATMMachineImpl();

  @Override
  public void addCashToATM(long amount) {
    if (isAdmin) {
      atmMachine.addCashToATM(amount);
    } else {
      logger.error("User is not an Admin");
      throw new RuntimeException("User is not an Admin");
    }
  }

  public boolean authenticateUser(String userName, String password) {
    if(userName.equals("Ashish") && password.equals("Welcome1")){
      isAdmin = true;
    }
    return isAdmin;
  }

  public boolean authenticateCard(String cardNo, int pin) {
    return false;
  }

  @Override
  public long getAccountBalance() {
    return 0;
  }

  @Override
  public void withDrawFromAccount(long amount) {

  }
}
