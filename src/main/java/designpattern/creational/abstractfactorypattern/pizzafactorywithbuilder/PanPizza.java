package designpattern.creational.abstractfactorypattern.pizzafactorywithbuilder;

public class PanPizza implements Pizza {
  String toppings;
  double price;
  double size;
  String seasonings;

  public PanPizza(String toppings, double price, double size, String seasonings) {
    this.toppings = toppings;
    this.price = price;
    this.size = size;
    this.seasonings = seasonings;
  }

  @Override
  public String getToppings() {
    return toppings;
  }

  @Override
  public double getPrice() {
    return price;
  }

  @Override
  public double getSize() {
    return size;
  }

  @Override
  public String getSeasonings() {
    return seasonings;
  }
}
