package designpattern.behavioural.chainofresponsibilitypattern;

public class Dollar10Dispenser implements DispenseChain{
  private DispenseChain chain;

  @Override
  public void setNextChain(DispenseChain nextChain) {
    this.chain = nextChain;
  }

  @Override
  public void dispense(Currency currency) {
    if(currency.getAmount() >= 10){
      int num_of_Notes = currency.getAmount()/10;
      int remainder = currency.getAmount()%10;
      System.out.println("Dispensing "+num_of_Notes+ " 10$ notes");
      if(remainder!=0){
        this.chain.dispense(new Currency(remainder));
      }
    }else{
      this.chain.dispense(currency);
    }
  }
}
