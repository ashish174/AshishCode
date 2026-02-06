package codingRound.cartservice.model;

import codingRound.cartservice.constants.ItemType;

public class Medicine extends Item {

  public Medicine(String name, double priceBeforeTax) {
    super(name, priceBeforeTax);
  }

  @Override
  public ItemType getItemType() {
    return ItemType.MEDICINE;
  }
}
