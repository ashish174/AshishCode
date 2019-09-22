package AshishKart.model.priceaftertax;

import AshishKart.model.products.Cloth;
import AshishKart.model.products.Toy;

public class ProductPriceAfterTaxCalculator implements PriceAfterTaxCalculator {
  @Override
  public double getPriceAfterTax(Cloth cloth) {
    return cloth.getPrice()+cloth.getTax() - cloth.getDiscount();
  }

  @Override
  public double getPriceAfterTax(Toy toy) {
    return toy.getPrice()+toy.getTax() + toy.getDiscount();
  }
}
