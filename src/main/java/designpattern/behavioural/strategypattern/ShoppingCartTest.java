package designpattern.behavioural.strategypattern;


import designpattern.structural.proxypattern.CommandExecutor;
import designpattern.structural.proxypattern.CommandExecutorProxy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



public class ShoppingCartTest {
  private static Logger logger = LogManager.getLogger(ShoppingCartTest.class);
  public static void main(String[] args) {
    ShoppingCart cart = new ShoppingCart();
    Item item1 = new Item("1111",   150);
    Item item2 = new Item("1117",   170);
    Item item3 = new Item("2121",   80);

    cart.addItem(item1);
    cart.addItem(item2);
    cart.addItem(item3);

    //pay by paypal
    cart.pay(new PaypalStrategy("ashish", "Welcome1"));

    //pay by credit card
    cart.pay(new CreditCardStrategy("ashish", "4123-5423-2156-3241", "231", "02/21"));
  }
}
