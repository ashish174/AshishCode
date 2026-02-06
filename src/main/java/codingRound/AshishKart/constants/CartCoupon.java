package codingRound.AshishKart.constants;

public enum  CartCoupon {
  DIWALISALE(10),
  WEEKENDOFF(5);


  private int discount;

  CartCoupon(int discount) {
    this.discount = discount;
  }

  public int getDiscount() {
    return discount;
  }
}
