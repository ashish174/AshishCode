package designpattern.structural.decoratorpattern.shoppingcart;

public class GiftCodeDiscount extends DiscountDecorator {
    public GiftCodeDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public int getAmount() {
        int discountTillNow = this.discount.getAmount();
        System.out.println("Apply Gift Discount");
        return discountTillNow + 10 ;

    }

}
