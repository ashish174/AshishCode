package designpattern.structural.facadepattern;

import java.sql.Connection;

public class MySqlHelper {
  public static Connection getMySqlDBConnection(){
    //get MySql DB connection using connection parameters
    return null;
  }

  public void generateMySqlPdfReport(String tableName, Connection connection){
    //get data from table and generate pdf report
  }

  public void generateMySqlHtmlReport(String tableName, Connection connection){
    //get data from table and generate pdf report
  }
}
