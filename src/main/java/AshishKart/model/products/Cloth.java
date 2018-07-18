package AshishKart.model.products;

import AshishKart.model.discount.DiscountCalculator;
import AshishKart.model.tax.TaxCalculator;

public class Cloth implements Product {
  long id;
  String name;
  String description;
  double price;
  double tax;
  double discount;
  double PriceAfterTax;

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public Double getPrice() {
    return price;
  }

  @Override
  public Double getTax(TaxCalculator taxCalculator) {
    return taxCalculator.getTax(this);
  }

  @Override
  public Double getDiscount(DiscountCalculator discountCalculator) {
    return null;
  }

  @Override
  public Double getPriceAfterTax() {
    return getPrice();
  }
}
