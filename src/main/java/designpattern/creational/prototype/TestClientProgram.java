package designpattern.creational.prototype;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class TestClientProgram {
  private static Logger logger = LogManager.getLogger(TestClientProgram.class);
  public static void main(String[] args) {

    Employees employees = new Employees();
    employees.loadData();

    try {
      Employees employeesCopy1 = (Employees) employees.clone();
      Employees employeesCopy2 = (Employees) employees.clone();

      employeesCopy1.getEmpList().add("Johny");
      employeesCopy2.getEmpList().add("Rai");

      logger.info("Employees "+employees.getEmpList());
      logger.info("Employees Copy1 "+employeesCopy1.getEmpList());
      logger.info("Employees Copy2 "+employeesCopy2.getEmpList());

    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }



  }
}
