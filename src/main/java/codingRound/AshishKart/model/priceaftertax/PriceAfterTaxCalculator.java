package codingRound.AshishKart.model.priceaftertax;

import codingRound.AshishKart.model.products.Cloth;
import codingRound.AshishKart.model.products.Toy;

public interface PriceAfterTaxCalculator {
  double getPriceAfterTax(Cloth cloth);
  double getPriceAfterTax(Toy toy);
}
