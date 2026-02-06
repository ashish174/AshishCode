package codingRound.AshishKart.model.cart;

import codingRound.AshishKart.constants.CartCoupon;

public class CartCouponDecorator extends CartPrice {
  CartCoupon cartCoupon;

  public CartCouponDecorator(CartSummary cartSummary) {
    super(cartSummary);
  }

  @Override
  public double getFinalCartPrice() {
    return cartSummary.getPriceAfterTax()*(1+cartCoupon.getDiscount()/100);
  }
}
