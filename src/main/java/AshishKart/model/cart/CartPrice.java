package AshishKart.model.cart;

public abstract class CartPrice {
  CartSummary cartSummary;

  public CartPrice(CartSummary cartSummary) {
    this.cartSummary = cartSummary;
  }

  public abstract double getFinalCartPrice();
}
