package lldcodingRound.AshishKart.model.cart;

import lldcodingRound.AshishKart.constants.CartCoupon;

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
