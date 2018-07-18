package designpattern.creational.abstractfactorypattern.pizzafactorywithbuilder;

public interface AbstractPizzaFactory {
  Pizza createPizza();
  Pizza createPizzaUsingBuilder();
}
