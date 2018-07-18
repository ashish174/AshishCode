package designpattern.creational.abstractfactorypattern.pizzafactorywithbuilder;

public class PizzaBuilder {
  private String toppings;
  private double price;
  private double size;
  private String seasonings;

  public PizzaBuilder setToppings(String toppings) {
    this.toppings = toppings;
    return this;
  }

  public PizzaBuilder setPrice(double price) {
    this.price = price;
    return this;
  }

  public PizzaBuilder setSize(double size) {
    this.size = size;
    return this;
  }

  public PizzaBuilder setSeasonings(String seasonings) {
    this.seasonings = seasonings;
    return this;
  }

  public String getToppings() {
    return toppings;
  }

  public double getPrice() {
    return price;
  }

  public double getSize() {
    return size;
  }

  public String getSeasonings() {
    return seasonings;
  }

  public Pizza getCheesePizza(){
    return new CheesePizza(toppings, price, size, seasonings);
  }

  public Pizza getChickenPizza(){
    return new ChickenPizza(toppings, price, size, seasonings);
  }

}
