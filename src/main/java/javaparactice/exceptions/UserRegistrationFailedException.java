package javaparactice.exceptions;

import org.springframework.http.HttpStatus;

public class UserRegistrationFailedException extends Exception{
  private String title;
  private String details;
  private HttpStatus httpStatus;
  private Problem problem;

  public UserRegistrationFailedException(String message, String title) {
    super(message);
    this.title = title;
  }

  public UserRegistrationFailedException(String message, String title, HttpStatus httpStatus) {
    super(message);
    this.title = title;
    this.httpStatus = httpStatus;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

  public Problem getProblem() {
    return problem;
  }

  public void setProblem(Problem problem) {
    this.problem = problem;
  }
}
