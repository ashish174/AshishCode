package designpattern.structural.facadepattern;

import java.sql.Connection;

public class OracleHelper {
  public static Connection getOracleDBConnection(){
    //get Oracle DB connection using connection parameters
    return null;
  }

  public void generateOraclePdfReport(String tableName, Connection connection){
    //get data from table and generate pdf report
  }

  public void generateOracleHtmlReport(String tableName, Connection connection){
    //get data from table and generate pdf report
  }
}
