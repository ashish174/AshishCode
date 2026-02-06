package codingRound.AshishKart.model.tax;

import codingRound.AshishKart.model.products.Cloth;
import codingRound.AshishKart.model.products.Toy;

public interface TaxCalculator {
  double getTax(Cloth cloth);
  double getTax(Toy toy);
}
