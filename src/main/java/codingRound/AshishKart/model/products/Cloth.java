package codingRound.AshishKart.model.products;

import codingRound.AshishKart.model.discount.DiscountCalculator;
import codingRound.AshishKart.model.discount.ProductDiscountCalculator;
import codingRound.AshishKart.model.priceaftertax.PriceAfterTaxCalculator;
import codingRound.AshishKart.model.priceaftertax.ProductPriceAfterTaxCalculator;
import codingRound.AshishKart.model.tax.ProductTaxCalculator;
import codingRound.AshishKart.model.tax.TaxCalculator;

public class Cloth implements Product {
  private long id;
  private String name;
  private String description;
  private double price;
  private double tax;
  private double discount;
  private double priceAfterTax;

  public Cloth(ClothBuilder clothBuilder) {
    name = clothBuilder.name;
    description = clothBuilder.description;
    price = clothBuilder.price;
    tax = calculateTax(new ProductTaxCalculator());
    discount = calculateDiscount(new ProductDiscountCalculator());
    priceAfterTax = calculatePriceAfterTax(new ProductPriceAfterTaxCalculator());
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
    return this.priceAfterTax;
  }

  public static class ClothBuilder {
    private String name;
    private String description;
    private double price;

    public ClothBuilder setName(String name) {
      this.name = name;
      return this;
    }

    public ClothBuilder setDescription(String description) {
      this.description = description;
      return this;
    }

    public ClothBuilder setPrice(double price) {
      this.price = price;
      return this;
    }

    public Cloth getInstance(){
      return new Cloth(this);
    }


  }
}
