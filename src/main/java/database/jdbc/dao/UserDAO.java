package database.jdbc.dao;

import database.jdbc.DBUtil;
import database.jdbc.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
  Logger logger = LoggerFactory.getLogger(UserDAO.class);

  public List<User> getUsers(){
    List<User> users = new ArrayList<>();
    try {
      Connection conn = DBUtil.getConnection();
      Statement st = conn.createStatement();
      String query = "select * from User ";
      ResultSet rs = st.executeQuery(query);
      while (rs.next()){
        User user = new User();
        user.setUserName(rs.getString("USERNAME"));
        user.setPassword(rs.getString("PASSWORD"));
        user.setJoinedOn(rs.getDate("JOINED_ON"));
        users.add(user);
        logger.info("{}", user);
      }
      DBUtil.closeConnection(conn, st);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return users;
  }

  public List<User> getUser(String userName){
    List<User> users = new ArrayList<>();
    try {
      Connection conn = DBUtil.getConnection();
      Statement st = conn.createStatement();
      String query = "select * from User where username = '"+userName+"'";
      ResultSet rs = st.executeQuery(query);
      while (rs.next()){
        User user = new User();
        user.setUserName(rs.getString("USERNAME"));
        user.setPassword(rs.getString("PASSWORD"));
        user.setJoinedOn(rs.getDate("JOINED_ON"));
        users.add(user);
        logger.info("{}", user);
      }
      DBUtil.closeConnection(conn, st);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return users;
  }

  public int saveUser(User user){
    List<User> users = new ArrayList<>();
    int rowAffected = 0;
    try {
      logger.info("User To Add: {}", user);

      Connection conn = DBUtil.getConnection();
      String query = "Insert into User values (?, ?, ?)";
      PreparedStatement pst = conn.prepareStatement(query);
      pst.setString(1, user.getUserName());
      pst.setString(2, user.getPassword());
      pst.setDate(3,  new Date(user.getJoinedOn().getTime()));

      rowAffected = pst.executeUpdate();
      logger.info("Row Affected: {}", rowAffected);
      DBUtil.closeConnection(conn, pst);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rowAffected;
  }

  public int deleteUser(String userName){
    List<User> users = new ArrayList<>();
    int rowAffected = 0;
    try {
      logger.info("UserName To delete: {}", userName);

      Connection conn = DBUtil.getConnection();
      String query = "delete from User where username = '"+userName+"'";
      Statement st = conn.createStatement();

      rowAffected = st.executeUpdate(query);
      logger.info("Row Affected: {}", rowAffected);
      DBUtil.closeConnection(conn, st);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rowAffected;
  }
}
