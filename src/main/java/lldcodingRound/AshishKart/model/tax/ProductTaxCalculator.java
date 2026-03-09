package lldcodingRound.AshishKart.model.tax;

import lldcodingRound.AshishKart.constants.TaxSlab;
import lldcodingRound.AshishKart.model.products.Cloth;
import lldcodingRound.AshishKart.model.products.Toy;

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
