package designpattern.structural.facadepattern;


import designpattern.structural.adapterpattern.SocketAdapter;
import designpattern.structural.adapterpattern.SocketClassAdapterImpl;
import designpattern.structural.adapterpattern.SocketObjectAdapterImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;


public class TestClientProgram {
  private static Logger logger = LogManager.getLogger(TestClientProgram.class);
  public static void main(String[] args) {
    String tableName = "Employee";

    //generating MySql HTML report and Oracle PDF report without using Facade
    Connection connection = MySqlHelper.getMySqlDBConnection();
    MySqlHelper mySqlHelper = new MySqlHelper();
    mySqlHelper.generateMySqlHtmlReport(tableName, connection);

    Connection connection1 = OracleHelper.getOracleDBConnection();
    OracleHelper oracleHelper = new OracleHelper();
    oracleHelper.generateOraclePdfReport(tableName, connection);


    //generating MySql HTML report and Oracle PDF report using Facade
    HelperFacade.generateReport(DBTypes.MYSQL, ReportTypes.HTML, tableName);
    HelperFacade.generateReport(DBTypes.ORACLE, ReportTypes.PDF, tableName);


  }
}
