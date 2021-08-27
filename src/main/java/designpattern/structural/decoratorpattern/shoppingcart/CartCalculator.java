package designpattern.structural.decoratorpattern.shoppingcart;

public class CartCalculator implements Discount{
    @Override
    public int getAmount() {
        System.out.println("Adding Cart Value");
        return 100;
    }
}
