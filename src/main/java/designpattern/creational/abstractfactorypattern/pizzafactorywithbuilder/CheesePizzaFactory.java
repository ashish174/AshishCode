package designpattern.creational.abstractfactorypattern.pizzafactorywithbuilder;

public class CheesePizzaFactory implements AbstractPizzaFactory {
  PizzaBuilder pizzaBuilder;

  public CheesePizzaFactory() {
  }

  public CheesePizzaFactory(PizzaBuilder builder) {
    pizzaBuilder = builder;
  }

  @Override
  public Pizza createPizza() {
    //return new CheesePizza("",90,8,"");
    return new PizzaBuilder().setToppings("Cheese").setSize(8).setSeasonings("Oregano").setPrice(100).getCheesePizza();
  }

  @Override
  public Pizza createPizzaUsingBuilder() {
    return new CheesePizza(pizzaBuilder);
  }
}
