package designpattern.creational.abstractfactorypattern.pizzafactorywithbuilder;

public class ChickenPizzaFactory implements AbstractPizzaFactory {
  @Override
  public Pizza createPizza() {
    return new PizzaBuilder().setToppings("Cheese").setSize(8).setSeasonings("Oregano").setPrice(100).getChickenPizza();
  }

  @Override
  public Pizza createPizzaUsingBuilder() {
    return null;
  }
}
