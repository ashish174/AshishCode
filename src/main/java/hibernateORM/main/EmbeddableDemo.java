package hibernateORM.main;

import hibernateORM.dao.PersonDAO;
import hibernateORM.entity.Name;
import hibernateORM.entity.Person;

public class EmbeddableDemo {
  public static void main(String[] args) {
    PersonDAO personDAO = new PersonDAO();
    Name name = new Name("ashish", "mangal", "pandey");
    Person person = new Person(123, name);
    personDAO.savePerson(person);
  }
}
