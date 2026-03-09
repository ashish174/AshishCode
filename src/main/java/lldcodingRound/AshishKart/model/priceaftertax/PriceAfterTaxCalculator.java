package lldcodingRound.AshishKart.model.priceaftertax;

import lldcodingRound.AshishKart.model.products.Cloth;
import lldcodingRound.AshishKart.model.products.Toy;

public interface PriceAfterTaxCalculator {
  double getPriceAfterTax(Cloth cloth);
  double getPriceAfterTax(Toy toy);
}
