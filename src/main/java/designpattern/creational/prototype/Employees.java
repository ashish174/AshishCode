package designpattern.creational.prototype;

import java.util.ArrayList;
import java.util.List;


/*
when the Object creation is a costly affair and requires a lot of time and resources
and you have a similar object already existing.
It provides a mechanism to copy the original object to a new object and then modify it according to our needs.
Prototype design pattern uses java cloning to copy the object.
Copy can be deep or shallow as per our needs.
Ex: Getting  data from DB in our program multiple times and doing manipulations with such data multiple times
Ex: Collection.stream() API creates a copy of collection
*/

public class Employees implements Cloneable{
  private List<String> empList;

  public Employees() {
    this.empList = new ArrayList<>();
  }

  public Employees(List<String> empList) {
    this.empList = empList;
  }

  public void loadData(){
    //read all employees from database and put into the list
    empList.add("Ram");
    empList.add("Anshu");
    empList.add("Gattu");
    empList.add("Chantu");
  }

  public List<String> getEmpList() {
    return empList;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    List<String> empListCopy = new ArrayList<>();
    for(String s : empList){
      empListCopy.add(s);
    }
    return new Employees(empListCopy);
  }
}
