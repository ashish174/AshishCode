package database.hibernateORM.entity;

import javax.persistence.Id;

//@Entity
public class Person {
  @Id
  int rollno;
  Name name;

  public Person() {
  }

  public Person(int rollno, Name name) {
    this.rollno = rollno;
    this.name = name;
  }

  public int getRollno() {
    return rollno;
  }

  public void setRollno(int rollno) {
    this.rollno = rollno;
  }

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Student{" +
        "rollno=" + rollno +
        ", name='" + name + '\'' +
        '}';
  }
}
