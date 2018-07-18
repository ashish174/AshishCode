package cartservice.constants;

public enum ItemType {

  MEDICINE(0),
  GROCERY(20),
  BOOK(20);

  private double taxRate;

  ItemType(int taxRate) {
    this.taxRate = taxRate;
  }

  public double getTaxRate() {
    return taxRate;
  }
}
