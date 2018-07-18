package designpattern.behavioural.interpretorpattern;

public class IntToBinaryExpression implements Expression{
  private int anInt;

  public IntToBinaryExpression(int anInt) {
    this.anInt = anInt;
  }

  @Override
  public String interpret(InterpreterContext interpreterContext) {
    return interpreterContext.getBinaryFormat(anInt);
  }
}
