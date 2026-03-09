package lldcodingRound.AshishKart.model.discount;

import lldcodingRound.AshishKart.model.products.Cloth;
import lldcodingRound.AshishKart.model.products.Toy;

public interface DiscountCalculator {
  double getDiscount(Cloth cloth);
  double getDiscount(Toy toy);
}
