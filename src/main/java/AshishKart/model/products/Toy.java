package AshishKart.model.products;

import AshishKart.model.discount.DiscountCalculator;
import AshishKart.model.priceaftertax.PriceAfterTaxCalculator;
import AshishKart.model.tax.TaxCalculator;

public class Toy implements Product {

  long id;
  String name;
  String description;
  double price;
  private double tax;
  private double discount;
  private double PriceAfterTax;

  public Toy(ToyBuilder toyBuilder) {
    name = toyBuilder.name;
    description = toyBuilder.description;
    price = toyBuilder.price;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public Double getPrice() {
    return price;
  }

  @Override
  public Double calculateTax(TaxCalculator taxCalculator) {
    return taxCalculator.getTax(this);
  }

  @Override
  public Double calculateDiscount(DiscountCalculator discountCalculator) {
    return discountCalculator.getDiscount(this);
  }

  @Override
  public Double calculatePriceAfterTax(PriceAfterTaxCalculator priceAfterTaxCalculator) {
    return priceAfterTaxCalculator.getPriceAfterTax(this);
  }

  @Override
  public Double getTax() {
    return this.tax;
  }

  @Override
  public Double getDiscount() {
    return this.discount;
  }

  @Override
  public Double getPriceAfterTax() {
    return this.PriceAfterTax;
  }

  public static class ToyBuilder{
    String name;
    String description;
    double price;

    public ToyBuilder setName(String name) {
      this.name = name;
      return this;
    }

    public ToyBuilder setDescription(String description) {
      this.description = description;
      return this;
    }

    public ToyBuilder setPrice(double price) {
      this.price = price;
      return this;
    }

    public Toy getInstance(){
      return new Toy(this);
    }
  }
}
