package AshishKart.model.cart;

import AshishKart.model.products.Product;

public class CartItemImpl implements CartItem {

  Product product;
  int quantity;
  private double price;
  private double tax;
  private double discount;
  private double priceAfterTax;

  @Override
  public double getPrice() {
    return 0;
  }

  @Override
  public double getTax() {
    return 0;
  }

  @Override
  public double getDiscount() {
    return 0;
  }

  @Override
  public double getPriceAfterTax() {
    return 0;
  }
}
