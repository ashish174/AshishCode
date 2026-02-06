package codingRound.AshishKart.constants;

public enum ProductDiscount {
  CLOTH(20),
  TOY(30),
  OTHER(10);

  private int discount;

  ProductDiscount(int discount) {
    this.discount = discount;
  }

  public int getDiscount() {
    return discount;
  }
}
