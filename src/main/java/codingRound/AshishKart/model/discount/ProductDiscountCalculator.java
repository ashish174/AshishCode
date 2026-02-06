package codingRound.AshishKart.model.discount;

import codingRound.AshishKart.constants.ProductDiscount;
import codingRound.AshishKart.model.products.Cloth;
import codingRound.AshishKart.model.products.Toy;

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
