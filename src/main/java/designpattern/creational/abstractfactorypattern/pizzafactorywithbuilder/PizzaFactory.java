package designpattern.creational.abstractfactorypattern.pizzafactorywithbuilder;

public class PizzaFactory {

  public static Pizza getPizzaInstance(AbstractPizzaFactory factory) {
    return factory.createPizza();
  }

  public static Pizza getPizzaInstanceUsingBuilder(AbstractPizzaFactory factory) {
    return factory.createPizzaUsingBuilder();
  }

}
