package codingRound.AshishKart.model.products;

import codingRound.AshishKart.model.discount.DiscountCalculator;
import codingRound.AshishKart.model.priceaftertax.PriceAfterTaxCalculator;
import codingRound.AshishKart.model.tax.TaxCalculator;

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
