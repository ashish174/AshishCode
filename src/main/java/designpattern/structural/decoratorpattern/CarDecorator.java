package designpattern.structural.decoratorpattern;

/*
It is used to modify the functionality of an object at runtime.
At the same time other instances of the same class will not be affected by this
,so individual object gets the modified behavior.
It uses abstract classes or interface with composition to implement.
Using Decorator, we can add  any new functionality or remove any existing behavior of an Object and not all instances at runtime
-Component Interface
-Component Implementation
-Decorator - implements the component interface and it has a HAS-A relationship with the component interface.
The component variable should be accessible to the child decorator classes, so we will make this variable protected.
-Concrete Decorator - Extending the base decorator functionality and modifying the component behavior accordingly.
We can have concrete decorator classes as LuxuryCar and SportsCar.
Car -> BasicCar
  1.SportsCar
  2. LuxuryCar
What if we want a Car with both Sports and Luxury features. This requires decorator pattern.
Decorator design pattern is helpful in providing runtime modification abilities and hence more flexible.
Its easy to maintain and extend when the number of choices are more.
The disadvantage of decorator design pattern is that it uses a lot of similar kind of objects (decorators).
Decorator pattern is used a lot in Java IO classes, such as FileReader, BufferedReader etc.
*/
public class CarDecorator implements Car{
  protected Car car;

  public CarDecorator(Car car) {
    this.car = car;
  }

  @Override
  public void assemble() {
    this.car.assemble();
  }

  @Override
  public int getCost() {
    return this.car.getCost();
  }
}
