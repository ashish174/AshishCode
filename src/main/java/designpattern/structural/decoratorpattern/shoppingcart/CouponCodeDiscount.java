package designpattern.structural.decoratorpattern.shoppingcart;

public class CouponCodeDiscount extends DiscountDecorator {
    public CouponCodeDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public int getAmount() {
        int discountTillNow = this.discount.getAmount();
        System.out.println("Apply Coupon code Discount");
        return 5 + discountTillNow;
    }

}
