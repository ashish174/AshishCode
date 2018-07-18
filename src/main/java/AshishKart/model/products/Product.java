package AshishKart.model.products;

import AshishKart.model.discount.DiscountCalculator;
import AshishKart.model.tax.TaxCalculator;

public interface Product {
  String getName();
  String getDescription();
  Double getPrice();
  Double getTax(TaxCalculator taxCalculator);
  Double getDiscount(DiscountCalculator discountCalculator);
  Double getPriceAfterTax();

}
