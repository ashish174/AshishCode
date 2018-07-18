package designpattern.behavioural.visitorpattern;


import designpattern.structural.proxypattern.CommandExecutor;
import designpattern.structural.proxypattern.CommandExecutorProxy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/*
It is used when we have to perform an operation on a group of similar kind of Objects.
Here, we can move the operational logic from the objects to another class.
Ex: a Shopping cart where we can add different type of items (Elements).
When we click on checkout button, it calculates the total amount to be paid.
Now calculation logic can be in item classes or we can move out this logic to another class using visitor pattern.
benefit of this pattern is that if the logic of operation changes, then we need to make change only in the visitor implementation rather than doing it in all the item classes.
Another benefit is that adding a new item to the system is easy, it will require change only in visitor interface and implementation,
Existing item classes will not be affected.
*/


public class ShoppingCartClient {
  private static Logger logger = LogManager.getLogger(ShoppingCartClient.class);
  public static void main(String[] args) {

    ItemElement[] items = new ItemElement[]{
        new Book(170, "12345"),
        new Fruit(25, 5, "Mango"),
        new Fruit(50, 3, "Grapes")
    };
    int total = calculatePrice(items);
    System.out.println("Total getCost "+total);

  }

  private static int calculatePrice(ItemElement[] items){
    ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
    int sum=0;
    for(ItemElement item : items){
      sum = sum + item.accept(visitor);
    }
    return sum;
  }
}
