package AshishKart.model.products;

import AshishKart.model.tax.TaxCalculator;

public class Toy implements Product {

  long id;
  String name;
  String description;
  double price;

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
}
