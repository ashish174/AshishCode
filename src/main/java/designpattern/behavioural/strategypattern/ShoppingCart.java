package designpattern.behavioural.strategypattern;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
  List<Item> items;

  public ShoppingCart() {
    items = new ArrayList<>();
  }

  public void addItem(Item item){
    items.add(item);
  }

  public void removeItem(Item item){
    items.remove(item);
  }

  public int calculateTotal(){
    int sum=0;
    for(Item item : items){
      sum = sum+item.getPrice();
    }
    return sum;
  }

  public void pay(PaymentStrategy paymentStrategy){
    int amount = calculateTotal();
    paymentStrategy.pay(amount);
  }


}
