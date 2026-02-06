package codingRound.oopsdesign.flipkart.models.inventory;

public interface Item {
    String getName();
    String getDescription();
    double getPrice();
    double getDiscount();
    double getPriceAfterDiscount();
}
