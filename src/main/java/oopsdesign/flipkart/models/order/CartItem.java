package oopsdesign.flipkart.models.order;

import oopsdesign.flipkart.models.inventory.objects.ProductItem;

public class CartItem {
    private ProductItem productItem;
    private int quantity;
    private float price;

    double getPrice(){
        return productItem.getPrice();
    }
    double getDiscount(){
        return productItem.getDiscount();
    }
    double getPriceAfterDiscount(){
        return productItem.getPriceAfterDiscount();
    }
}
