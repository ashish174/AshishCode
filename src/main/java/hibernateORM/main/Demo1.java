package hibernateORM.main;

import hibernateORM.dao.UserDAO;

public class Demo1 {
  public static void main(String[] args) {
    UserDAO userDAO = new UserDAO();
    userDAO.getUser("Ashish");
  }
}
