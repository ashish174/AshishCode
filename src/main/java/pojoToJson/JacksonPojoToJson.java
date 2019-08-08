package pojoToJson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JacksonPojoToJson {



  public static void main(String[] args) {
    // Create ObjectMapper
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    String json;
    Student s1 = new Student(1, "Ashish");
    Student s2 = new Student(2, "Ashu");
    List<Student> studentList = Arrays.asList(s1, s2);

    Students studentObj = new Students();
    studentObj.setStudents(studentList);

    License license = new License();
    List<License> licenseList = Arrays.asList(license);
    Licenses licenses = new Licenses();
    licenses.setLicenses(licenseList);


    {
      try {
        json = mapper.writeValueAsString(studentObj);
        System.out.println(json);
        json = mapper.writeValueAsString(licenses);
        System.out.println(json);


      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
    }
  }


}

class Students{
  List<Student> students;

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }
}

 class Student{
  int id;
  String name;

   public Student(int id, String name) {
     this.id = id;
     this.name = name;
   }

   public int getId() {
     return id;
   }

   public void setId(int id) {
     this.id = id;
   }

   public String getName() {
     return name;
   }

   public void setName(String name) {
     this.name = name;
   }
 }
