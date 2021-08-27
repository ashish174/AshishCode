package designpattern.structural.decoratorpattern.shoppingcart;

public class TestCartDiscountCalculator {
    public static void main(String[] args) {
        CouponCodeDiscount couponCodeDiscount = new CouponCodeDiscount(new GiftCodeDiscount(new CartCalculator()));
        couponCodeDiscount.getAmount();

    }
}
