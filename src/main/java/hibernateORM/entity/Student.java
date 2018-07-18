package hibernateORM.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
  @Id
  int rollNo;
  String name;
  int marks;
  //@OneToOne
  @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
  List<Laptop> laptops = new ArrayList<>();

  public Student() {
  }

  public Student(int rollNo, String name, int marks) {
    this.rollNo = rollNo;
    this.name = name;
    this.marks = marks;
  }

  public int getRollNo() {
    return rollNo;
  }

  public void setRollNo(int rollNo) {
    this.rollNo = rollNo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getMarks() {
    return marks;
  }

  public void setMarks(int marks) {
    this.marks = marks;
  }

  public List<Laptop> getLaptops() {
    return laptops;
  }

  public void setLaptops(List<Laptop> laptops) {
    this.laptops = laptops;
  }

  @Override
  public String toString() {
    return "Student{" +
        "rollNo=" + rollNo +
        ", name='" + name + '\'' +
        ", marks=" + marks +
        ", laptops=" + laptops +
        '}';
  }
}
