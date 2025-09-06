package designpattern.creational.abstractfactorypattern;

/*
A consumer class that will provide the entry point for the client classes to create sub-classes.
Client -> ComputerFactory(AbstractFactory)

*/

public class ComputerFactory {
  public static Computer getComputer(ComputerAbstractFactory factory){
    return factory.createComputer();
  }
}
