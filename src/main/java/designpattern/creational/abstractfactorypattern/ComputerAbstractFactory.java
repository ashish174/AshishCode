package designpattern.creational.abstractfactorypattern;

/*
Abstract Factory is a Factory of factories.
Factory Pattern uses if/else or switch to create desired objects
Abstract Factory get rid of this if/else or switch.
we have a factory class for each subclass.
Abstract Factory return subclass based on input factory class.
Provides code for interface rather than implementation.
can be easily extended to accommodate more products, for example we can add another sub-class Laptop and a factory LaptopFactory.
Abstract Factory pattern is robust and avoid conditional logic of Factory pattern.
*/
public interface ComputerAbstractFactory {
  public Computer createComputer();
}
