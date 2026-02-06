package codingRound.cartservice.model;

import codingRound.cartservice.constants.ItemType;

public class ItemFactory {
  public static Item getInstance(ItemType itemType, String name, double priceBeforeTax) {
    switch (itemType){
      case MEDICINE:
        return new Medicine(name, priceBeforeTax);
      case GROCERY:
        return new Grocery(name, priceBeforeTax);
      case BOOK:
        return new Book(name, priceBeforeTax);
      //throw new RuntimeException("Invalid ItemType");
    }
    return null;
  }
}
