package javaparactice.exceptions;

public class IncorrectFileExtensionException extends RuntimeException {
  private String message;

  public IncorrectFileExtensionException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }

  @Override
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}