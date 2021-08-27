package oopsdesign.flipkart.models.inventory.objects;

import java.util.List;
import oopsdesign.flipkart.models.inventory.Item;
import oopsdesign.flipkart.models.inventory.Tag;

public abstract class ProductItem implements Item {
    public long id;
    public String name;
    public String desc;
    public ItemCategory category;
    public double price;
    public double discount;
    public double priceAfterDiscount;
    public List<Tag> tags;

    void addItem(ProductItem productItem){

    }


}
