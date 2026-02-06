package codingRound.cartservice.model;

import codingRound.cartservice.constants.ItemType;

public class Grocery extends Item {


  public Grocery(String name, double priceBeforeTax) {
    super(name, priceBeforeTax);
  }

  @Override
  public ItemType getItemType() {
    return ItemType.GROCERY;
  }
}
