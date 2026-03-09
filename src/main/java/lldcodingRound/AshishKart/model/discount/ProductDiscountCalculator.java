package lldcodingRound.AshishKart.model.discount;

import lldcodingRound.AshishKart.constants.ProductDiscount;
import lldcodingRound.AshishKart.model.products.Cloth;
import lldcodingRound.AshishKart.model.products.Toy;

public class ProductDiscountCalculator implements DiscountCalculator {
  @Override
  public double getDiscount(Cloth cloth) {
    return cloth.getPrice()* ProductDiscount.CLOTH.getDiscount();
  }

  @Override
  public double getDiscount(Toy toy) {
    return toy.getPrice()* ProductDiscount.TOY.getDiscount();
  }
}
