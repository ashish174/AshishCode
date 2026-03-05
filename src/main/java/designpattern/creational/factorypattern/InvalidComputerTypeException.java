package designpattern.creational.factorypattern;

public class InvalidComputerTypeException extends RuntimeException {

  public InvalidComputerTypeException() {
    super("Invalid computer type");
  }
  public InvalidComputerTypeException(String message) {
    super(message);
  }
}
