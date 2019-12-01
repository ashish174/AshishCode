package exceptions;

public class IncorrectFileNameException extends Exception{

  private String message;

  public IncorrectFileNameException(String message) {
    super(message);
  }

  @Override
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
