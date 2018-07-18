package cartservice;

import cartservice.constants.ItemType;
import cartservice.model.Cart;
import cartservice.model.LineItem;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartTest {
  Logger logger = LoggerFactory.getLogger(CartTest.class);


  public void setup(){

  }

  @Test
  public void testCartWithTwoInput(){
    Cart myCart = new Cart();
    myCart.getLineItems().add(new LineItem(ItemType.BOOK, "Java", 1, 30));
    myCart.getLineItems().add(new LineItem(ItemType.GROCERY, "Chocolate",1, 1));
    myCart.viewCart();
  }

  @Test
  public void testCartWithThreeItem(){
    Cart myCart = new Cart();
    myCart.getLineItems().add(new LineItem(ItemType.GROCERY, "Ink",1, 20));
    myCart.getLineItems().add(new LineItem(ItemType.MEDICINE, "Crocin", 1, 4));
    myCart.getLineItems().add(new LineItem(ItemType.GROCERY, "Stapler",1, 10));
    myCart.viewCart();
  }
}
