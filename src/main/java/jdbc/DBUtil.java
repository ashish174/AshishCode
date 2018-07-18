package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {


  public static Connection getConnection(){
    String url = "jdbc:mysql://localhost:3306/AshishDB?serverTimezone=UTC";
    String userName = "root";
    String password = "Ashish174";
    Connection conn = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(url, userName, password);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return conn;
  }

  public static void closeConnection(Connection conn, Statement st){
    try {
      st.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
