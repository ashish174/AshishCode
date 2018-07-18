package designpattern.structural.facadepattern;

import java.sql.Connection;

/*
Often too many Types/interfaces are confusing.
Facade Pattern is to help client applications to easily interact with the system.
Provide a unified interface to a set of interfaces in a subsystem.
Facade Pattern defines a higher-level interface that makes the subsystem easier to use.
It provide a wrapper interface on top of the existing interface to help client application.
Say we have diff DB connections and based on that we generate diff format results
Facade pattern interface is a lot easier and cleaner way to avoid having a lot of logic at client side.
Facade pattern can be applied at any point of development, usually when the number of interfaces grow and system gets complex.
Ex: JDBC Driver Manager class to get the database connection
Facade design pattern should be applied for similar kind of interfaces,
its purpose is to provide a single interface rather than multiple interfaces that does the similar kind of jobs.

*/
public class HelperFacade {
  public static void generateReport(DBTypes dbType, ReportTypes reportType, String tableName){
    Connection connection = null;
    switch (dbType){
      case MYSQL:
        connection = MySqlHelper.getMySqlDBConnection();
        MySqlHelper mySqlHelper = new MySqlHelper();
        switch (reportType){
          case PDF:
            mySqlHelper.generateMySqlPdfReport(tableName, connection);
            break;
          case HTML:
            mySqlHelper.generateMySqlHtmlReport(tableName, connection);
            break;
        }
        break;
      case ORACLE:
        connection = OracleHelper.getOracleDBConnection();
        OracleHelper oracleHelper = new OracleHelper();
        switch (reportType){
          case PDF:
            oracleHelper.generateOraclePdfReport(tableName, connection);
            break;
          case HTML:
            oracleHelper.generateOracleHtmlReport(tableName, connection);
            break;
        }
        break;
    }
  }
}
