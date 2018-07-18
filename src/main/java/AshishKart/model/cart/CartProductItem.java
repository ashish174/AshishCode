package AshishKart.model.cart;

import AshishKart.model.products.Product;

public class CartProductItem implements CartItem {

  Product product;
  int quantity;

  @Override
  public double getPrice() {
    return 0;
  }

  @Override
  public double getTax() {
    return 0;
  }

  @Override
  public double getPriceAfterTax() {
    return 0;
  }
}
