package AshishKart.model.priceaftertax;

import AshishKart.model.products.Cloth;
import AshishKart.model.products.Toy;

public class ProductPriceAfterTax implements PriceAfterTax {
  @Override
  public double getPriceAfterTax(Cloth cloth) {
    return 0;
  }

  @Override
  public double getPriceAfterTax(Toy toy) {
    return 0;
  }
}
