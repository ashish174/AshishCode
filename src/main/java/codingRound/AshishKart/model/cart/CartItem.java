package codingRound.AshishKart.model.cart;

public interface CartItem {
  double getPrice();
  double getTax();
  double getDiscount();
  double getPriceAfterTax();
}
