package codingRound.cartservice.model;

import codingRound.cartservice.constants.ItemType;

public abstract class Item {

  private long id;
  private String name;
  private double priceBeforeTax;
  private double tax;
  private double priceAfterTax;

  public Item(String name, double priceBeforeTax) {
    this.name = name;
    this.priceBeforeTax = priceBeforeTax;
    tax = priceBeforeTax *(getTaxRate()/100);
    priceAfterTax = priceBeforeTax + tax;
  }

  public Item(ItemBuilder itemBuilder) {
    this.priceBeforeTax = itemBuilder.getCostPrice();
    tax = priceBeforeTax *(getTaxRate()/100);
    priceAfterTax = priceBeforeTax + tax;
  }

  public String getName() {
    return name;
  }

  public abstract ItemType getItemType();

  double getTaxRate(){
    return getItemType().getTaxRate();
  }

  public double getPriceBeforeTax() {
    return priceBeforeTax;
  }

  public double getTax() {
    return tax;
  }

  public double getPriceAfterTax() {
    return priceAfterTax;
  }
}
