package concurrency.threadCommunication;

public class IsEven {
  private boolean value;

  public IsEven(boolean value) {
    this.value = value;
  }

  public boolean getValue() {
    return value;
  }

  public void setValue(boolean value) {
    this.value = value;
  }
}
