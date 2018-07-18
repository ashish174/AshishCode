package AshishKart.constants;

public enum TaxSlab {
  CLOTH(10),
  TOY(15),
  OTHER(20);

  private int taxRate;

  TaxSlab(int taxRate) {
    this.taxRate = taxRate;
  }

  public int getTaxRate() {
    return taxRate;
  }
}
