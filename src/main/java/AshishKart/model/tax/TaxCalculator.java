package AshishKart.model.tax;

import AshishKart.model.products.Cloth;
import AshishKart.model.products.Toy;

public interface TaxCalculator {
  double getTax(Cloth cloth);
  double getTax(Toy toy);
}
