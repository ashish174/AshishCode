package AshishKart.model.discount;

import AshishKart.constants.ProductDiscount;
import AshishKart.model.products.Cloth;
import AshishKart.model.products.Toy;

public interface DiscountCalculator {
  double getDiscount(Cloth cloth);
  double getDiscount(Toy toy);
}
