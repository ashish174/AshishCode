package designpattern.creational.abstractfactorypattern.pizzafactorywithbuilder;

public class TestClientProgram {
  public static void main(String[] args) {
    Pizza cheesePizza = PizzaFactory.getPizzaInstance(new CheesePizzaFactory());
    System.out.println(cheesePizza);

    Pizza cheesePizzaNew = PizzaFactory.getPizzaInstanceUsingBuilder(new CheesePizzaFactory(new PizzaBuilder().setToppings("Cheese").setSize(10).setSeasonings("Chilli").setPrice(120)));
    System.out.println(cheesePizzaNew);
  }
}
