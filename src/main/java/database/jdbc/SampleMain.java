package database.jdbc;

import database.jdbc.dao.UserDAO;

public class SampleMain {
  public static void main(String[] args) {
    UserDAO userDAO = new UserDAO();
    userDAO.getUsers();


    userDAO.getUser("Anshu");

    //userDAO.saveUser(new User("Raka", "Maaru", new Date()));
    userDAO.deleteUser("root");
    userDAO.getUsers();

  }
}
