package cartservice.model;

import cartservice.constants.ItemType;

public class Book extends Item {


  public Book(String name, double priceBeforeTax) {
    super(name, priceBeforeTax);
  }

  @Override
  public ItemType getItemType() {
    return ItemType.BOOK;
  }
}
