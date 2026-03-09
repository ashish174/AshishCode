package lldcodingRound.AshishKart.model.tax;

import lldcodingRound.AshishKart.model.products.Cloth;
import lldcodingRound.AshishKart.model.products.Toy;

public interface TaxCalculator {
  double getTax(Cloth cloth);
  double getTax(Toy toy);
}
