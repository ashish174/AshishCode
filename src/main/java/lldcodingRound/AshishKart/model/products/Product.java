package lldcodingRound.AshishKart.model.products;

import lldcodingRound.AshishKart.model.discount.DiscountCalculator;
import lldcodingRound.AshishKart.model.priceaftertax.PriceAfterTaxCalculator;
import lldcodingRound.AshishKart.model.tax.TaxCalculator;

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
