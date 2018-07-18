package cartservice.model;

import cartservice.constants.ItemType;

public class LineItem {
  Item item;
  int quantity;
  double totalPriceBeforeTax;
  double totalTax;
  double totalPriceAfterTax;

  public LineItem(ItemType itemType, String name, int quantity, double cost) {
    this.item = ItemFactory.getInstance(itemType, name, cost);
    this.quantity = quantity;
    totalPriceBeforeTax = quantity*item.getPriceBeforeTax();
    totalTax = quantity*item.getTax();
    totalPriceAfterTax = quantity*item.getPriceAfterTax();
  }

  public Item getItem() {
    return item;
  }

  public int getQuantity() {
    return quantity;
  }

  public double getTotalPriceBeforeTax() {
    return totalPriceBeforeTax;
  }

  public double getTotalTax() {
    return totalTax;
  }

  public double getTotalPriceAfterTax() {
    return totalPriceAfterTax;
  }

  @Override
  public String toString() {
    return "LineItem{" +
        "item=" + item.getItemType().toString() +
        ", quantity=" + quantity +
        ", totalPriceBeforeTax=" + totalPriceBeforeTax +
        ", totalTax=" + totalTax +
        ", totalPriceAfterTax=" + totalPriceAfterTax +
        "}";
  }
}
