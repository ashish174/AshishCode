package codingRound.AshishKart.model.discount;

import codingRound.AshishKart.model.products.Cloth;
import codingRound.AshishKart.model.products.Toy;

public interface DiscountCalculator {
  double getDiscount(Cloth cloth);
  double getDiscount(Toy toy);
}
