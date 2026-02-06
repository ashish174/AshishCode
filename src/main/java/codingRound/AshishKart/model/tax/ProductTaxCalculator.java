package codingRound.AshishKart.model.tax;

import codingRound.AshishKart.constants.TaxSlab;
import codingRound.AshishKart.model.products.Cloth;
import codingRound.AshishKart.model.products.Toy;

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
