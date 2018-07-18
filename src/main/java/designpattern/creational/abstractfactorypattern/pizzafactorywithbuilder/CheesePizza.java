package designpattern.creational.abstractfactorypattern.pizzafactorywithbuilder;

public class CheesePizza implements Pizza {
  String toppings;
  double price;
  double size;
  String seasonings;

  public CheesePizza(String toppings, double price, double size, String seasonings) {
    this.toppings = toppings;
    this.price = price;
    this.size = size;
    this.seasonings = seasonings;
  }

  public CheesePizza(PizzaBuilder builder) {
    toppings = builder.getToppings();
    price = builder.getPrice();
    size = builder.getSize();
    seasonings = builder.getSeasonings();
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

  @Override
  public String toString() {
    return "CheesePizza{" +
        "toppings='" + toppings + '\'' +
        ", price=" + price +
        ", size=" + size +
        ", seasonings='" + seasonings + '\'' +
        '}';
  }
}
