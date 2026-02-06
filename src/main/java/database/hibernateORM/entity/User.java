package database.hibernateORM.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class User {
  @Id
  String userName;
  @Column(name = "password")
  String password;
  @Column(name = "joined_on")
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
