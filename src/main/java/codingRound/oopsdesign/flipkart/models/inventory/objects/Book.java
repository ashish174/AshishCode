package codingRound.oopsdesign.flipkart.models.inventory.objects;

public class Book extends ProductItem {
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return desc;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getDiscount() {
        return discount;
    }

    @Override
    public double getPriceAfterDiscount() {
        return priceAfterDiscount;
    }
}
