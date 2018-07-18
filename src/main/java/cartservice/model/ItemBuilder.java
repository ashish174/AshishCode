package cartservice.model;

import cartservice.constants.ItemType;

public class ItemBuilder {
  ItemType itemType;
  double costPrice;

  public ItemBuilder setItemType(ItemType itemType) {
    this.itemType = itemType;
    return this;
  }

  public ItemBuilder setCostPrice(double costPrice) {
    this.costPrice = costPrice;
    return this;
  }

  public ItemType getItemType() {
    return itemType;
  }

  public double getCostPrice() {
    return costPrice;
  }

  public Item build(){
    //return new Item(this);
    return null;
  }
}
