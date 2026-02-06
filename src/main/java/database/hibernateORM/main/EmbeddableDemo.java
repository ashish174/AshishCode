package database.hibernateORM.main;

import database.hibernateORM.dao.PersonDAO;
import database.hibernateORM.entity.Name;
import database.hibernateORM.entity.Person;

public class EmbeddableDemo {
  public static void main(String[] args) {
    PersonDAO personDAO = new PersonDAO();
    Name name = new Name("ashish", "mangal", "pandey");
    Person person = new Person(123, name);
    personDAO.savePerson(person);
  }
}
