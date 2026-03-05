package designpattern.creational.factorypattern;
/*
This is a entry point for the client classes to create sub-classes.
Factory design pattern is used when we have a super class with multiple sub-classes
and based on input, we need to return one of the sub-class.
This pattern take out the responsibility of instantiation of a class
from client program to the factory class.
We can keep Factory Class Singleton so as to keep just one instance of Factory class.
Based on different input parameter, different subclass are created.
Provides code for interface rather than implementation.
Make implementation Code loosely Coupled and flexible
while Client Code is unaffected by changes
*/

public class ComputerFactory {
  public static Computer getComputer(ComputerType type, String ram, String hdd, String cpu){
    switch (type){
      case PC:
        return new PC(ram, hdd, cpu);
      case SERVER:
        return new Server(ram, hdd, cpu);
      default:
        System.out.println("Incorrect Computer Type");
        // Consider throwing a custom exception instead of returning null
        throw new InvalidComputerTypeException("Invalid Computer Type:"+type);
    }
  }
}
