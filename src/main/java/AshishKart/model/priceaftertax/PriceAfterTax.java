package AshishKart.model.priceaftertax;

import AshishKart.model.products.Cloth;
import AshishKart.model.products.Toy;

public interface PriceAfterTax {
  double getPriceAfterTax(Cloth cloth);
  double getPriceAfterTax(Toy toy);
}
