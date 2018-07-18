package concurrency.ProducerConsumer;

public class Message {
  private String Msg;
  private boolean wasNotified = false;

  public Message(String msg) {
    Msg = msg;
  }

  public String getMsg() {
    return Msg;
  }

  public void setMsg(String msg) {
    Msg = msg;
  }

  public boolean isWasNotified() {
    return wasNotified;
  }

  public void setWasNotified(boolean wasNotified) {
    this.wasNotified = wasNotified;
  }
}
