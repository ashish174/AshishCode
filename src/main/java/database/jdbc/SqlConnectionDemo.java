package database.jdbc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Date;

public class SqlConnectionDemo {
  private static Logger logger = LoggerFactory.getLogger(SqlConnectionDemo.class);

  public static void main(String[] args) throws Exception {
    String url = "jdbc:mysql://localhost:3306/AshishDB?serverTimezone=UTC";
    String userName = "root";
    String password = "welcome1";



    Class.forName("com.mysql.cj.jdbc.Driver");

    Connection conn = DriverManager.getConnection(url, userName, password);
    // Statement is used for static query
    Statement statement = conn.createStatement();

    /*String query1 = "Insert into User values ('Chahaa', 'Welcome1', sysdate())";
    int rowAffected = statement.executeUpdate(query1);
    logger.info("Row Affected {}", rowAffected);*/

    String username = "root";
    String pswd = "welcome1";
    String query2 = "Insert into User values (?, ?, ?)";
    // PreparedStatement is used for parametrized query/dynamic query
    PreparedStatement pst = conn.prepareStatement(query2);
    pst.setString(1, username);
    pst.setString(2, pswd);
    pst.setDate(3, new java.sql.Date(new Date().getTime()));
    int rowAffected1 = pst.executeUpdate();
    logger.info("Row Affected {}", rowAffected1);

    String query = "select * from User";
    ResultSet rs = statement.executeQuery(query);
    while(rs.next()){
        logger.info("{}       {}      {}", rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("JOINED_ON"));
      //System.out.println(rs.getString("USERNAME")+" "+ rs.getString("PASSWORD")+" "+rs.getString("JOINED_ON"));
    }


    statement.close();
    pst.close();
    conn.close();

  }

}
