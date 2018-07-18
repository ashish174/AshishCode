package designpattern.behavioural.interpretorpattern;

public class IntToHexExpression implements Expression{
  private int anInt;

  public IntToHexExpression(int anInt) {
    this.anInt = anInt;
  }

  @Override
  public String interpret(InterpreterContext interpreterContext) {
    return interpreterContext.getHexadecimalFormat(anInt);
  }
}
