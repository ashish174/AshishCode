package designpattern.behavioural.chainofresponsibilitypattern;

public class Dollar50Dispenser implements DispenseChain{
  private DispenseChain chain;

  @Override
  public void setNextChain(DispenseChain nextChain) {
    this.chain = nextChain;
  }

  @Override
  public void dispense(Currency currency) {
    if(currency.getAmount() >= 50){
      int num_of_Notes = currency.getAmount()/50;
      int remainder = currency.getAmount()%50;
      System.out.println("Dispensing "+num_of_Notes+ " 50$ notes");
      if(remainder!=0){
        this.chain.dispense(new Currency(remainder));
      }
    }else{
      this.chain.dispense(currency);
    }
  }
}
