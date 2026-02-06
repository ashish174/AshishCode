package codingRound.AshishKart.model.priceaftertax;

import codingRound.AshishKart.model.products.Cloth;
import codingRound.AshishKart.model.products.Toy;

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
