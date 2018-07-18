package cartservice.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Cart {
  Logger logger = LoggerFactory.getLogger(Cart.class);
  List<LineItem> lineItems;
  double totalCostPriceOfCartItems;
  double totalTaxOnCartItems;
  double totalSalesPriceOfCartItems;

  public Cart() {
    lineItems = new ArrayList<>();
  }

  public void calculateCartValues(){
    double costPriceSum = 0;
    double taxSum = 0;
    double salesPriceSum = 0;
    for(LineItem lineItem : lineItems){
      costPriceSum += lineItem.getTotalPriceBeforeTax();
      taxSum += lineItem.getTotalTax();
      salesPriceSum += lineItem.getTotalPriceAfterTax();
    }
    totalCostPriceOfCartItems = costPriceSum;
    totalTaxOnCartItems = taxSum;
    totalSalesPriceOfCartItems = salesPriceSum;
  }

  public void viewCart(){
    calculateCartValues();
    StringBuilder cartView = new StringBuilder();
    cartView.append("\n");
    for(LineItem lineItem : lineItems){
      cartView.append(lineItem.toString()).append("\n");
    }
    cartView.append("TotalAmountBeforeTaxes :").append(totalCostPriceOfCartItems).append("\n")
        .append("Total Taxes : ").append(totalTaxOnCartItems).append("\n")
        .append("Total Amount After Taxes :  ").append(totalSalesPriceOfCartItems).append("\n");
    logger.info("{}", cartView);

  }

  public List<LineItem> getLineItems() {
    return lineItems;
  }

  public double getTotalCostPriceOfCartItems() {
    return totalCostPriceOfCartItems;
  }

  public double getTotalTaxOnCartItems() {
    return totalTaxOnCartItems;
  }

  public double getTotalSalesPriceOfCartItems() {
    return totalSalesPriceOfCartItems;
  }

  @Override
  public String toString() {
    return "\nCart{\n" +
         lineItems +"\n"+
        "totalCostPriceOfCartItems=" + totalCostPriceOfCartItems +"\n"+
        "totalTaxOnCartItems=" + totalTaxOnCartItems +"\n"+
        "totalSalesPriceOfCartItems=" + totalSalesPriceOfCartItems +
        "\n}";
  }
}
