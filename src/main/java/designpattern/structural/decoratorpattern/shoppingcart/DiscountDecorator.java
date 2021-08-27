package designpattern.structural.decoratorpattern.shoppingcart;

public class DiscountDecorator implements Discount {
    Discount discount;

    public DiscountDecorator(Discount discount) {
        this.discount = discount;
    }

    @Override
    public int getAmount() {
        return discount.getAmount();
    }
}
