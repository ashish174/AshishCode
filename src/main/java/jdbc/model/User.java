package jdbc.model;

import java.util.Date;

public class User {
  String userName;
  String password;
  Date joinedOn;

  public User() {
  }

  public User(String userName, String password, Date joinedOn) {
    this.userName = userName;
    this.password = password;
    this.joinedOn = joinedOn;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Date getJoinedOn() {
    return joinedOn;
  }

  public void setJoinedOn(Date joinedOn) {
    this.joinedOn = joinedOn;
  }

  @Override
  public String toString() {
    return "User{" +
        "userName='" + userName + '\'' +
        ", password='" + password + '\'' +
        ", joinedOn=" + joinedOn +
        '}';
  }
}
