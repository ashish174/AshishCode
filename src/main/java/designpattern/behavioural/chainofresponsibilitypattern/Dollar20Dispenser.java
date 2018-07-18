package designpattern.behavioural.chainofresponsibilitypattern;

public class Dollar20Dispenser implements DispenseChain{
  private DispenseChain chain;

  @Override
  public void setNextChain(DispenseChain nextChain) {
    this.chain = nextChain;
  }

  @Override
  public void dispense(Currency currency) {
    if(currency.getAmount() >= 20){
      int num_of_Notes = currency.getAmount()/20;
      int remainder = currency.getAmount()%20;
      System.out.println("Dispensing "+num_of_Notes+ " 20$ notes");
      if(remainder!=0){
        this.chain.dispense(new Currency(remainder));
      }
    }else{
      this.chain.dispense(currency);
    }
  }
}
