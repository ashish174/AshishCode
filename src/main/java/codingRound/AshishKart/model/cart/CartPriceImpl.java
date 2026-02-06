package codingRound.AshishKart.model.cart;

public class CartPriceImpl extends CartPrice {
  public CartPriceImpl(CartSummary cartSummary) {
    super(cartSummary);
  }

  @Override
  public double getFinalCartPrice() {
    return cartSummary.getPriceAfterTax();
  }
}
