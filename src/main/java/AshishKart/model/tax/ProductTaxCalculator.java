package AshishKart.model.tax;

import AshishKart.constants.TaxSlab;
import AshishKart.model.products.Cloth;
import AshishKart.model.products.Toy;

public class ProductTaxCalculator implements TaxCalculator {
  @Override
  public double getTax(Cloth cloth) {
    return cloth.getPrice()* TaxSlab.CLOTH.getTaxRate();
  }

  @Override
  public double getTax(Toy toy) {
    return toy.getPrice()* TaxSlab.TOY.getTaxRate();
  }
}
