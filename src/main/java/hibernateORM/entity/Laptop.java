package hibernateORM.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class  Laptop {
  @Id
  private int lId;
  private String lName;
  /*@ManyToOne(mappedBy = "laptops")
  List<Student> students = new ArrayList<>();*/
  @ManyToOne
  private Student student;

  public Laptop() {
  }

  public Laptop(int lId, String lName) {
    this.lId = lId;
    this.lName = lName;
  }

  public int getlId() {
    return lId;
  }

  public void setlId(int lId) {
    this.lId = lId;
  }

  public String getlName() {
    return lName;
  }

  public void setlName(String lName) {
    this.lName = lName;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  /*public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }*/

  @Override
  public String toString() {
    return "Laptop{" +
        "lId=" + lId +
        ", lName='" + lName + '\'' +
        '}';
  }
}
