package AshishKart.model.products;

import AshishKart.model.discount.DiscountCalculator;
import AshishKart.model.priceaftertax.PriceAfterTaxCalculator;
import AshishKart.model.tax.TaxCalculator;

public interface Product {
  String getName();
  String getDescription();
  Double getPrice();
  Double calculateTax(TaxCalculator taxCalculator);
  Double calculateDiscount(DiscountCalculator discountCalculator);
  Double calculatePriceAfterTax(PriceAfterTaxCalculator priceAfterTaxCalculator);
  Double getTax();
  Double getDiscount();
  Double getPriceAfterTax();

}
