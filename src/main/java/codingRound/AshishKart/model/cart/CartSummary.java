package codingRound.AshishKart.model.cart;

import java.util.List;

public class CartSummary {
  private List<CartItem> cartItems;
  private double price;
  private double tax;
  private double priceAfterTax;

  public double getPrice() {
    return price;
  }

  public double getTax() {
    return tax;
  }

  public double getPriceAfterTax() {
    return priceAfterTax;
  }
}
