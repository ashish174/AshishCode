package designpattern.behavioural.chainofresponsibilitypattern.logger;

public class TestLogger {
  public static void main(String[] args) {
    MyLogger myLogger = new MyLogger();
    Logger logger = myLogger.getLogger();
    logger.logMessage(LogType.INFO, "Starting Program");
    logger.logMessage(LogType.ERROR, "Encountered a Error");
    logger.logMessage(LogType.DEBUG, "Printing Json");
    logger.logMessage(LogType.DEBUG, "Printing Values");

    Logger logger1 = MyLogger.getLogger1();
    logger1.logMessage(LogType.INFO, "Starting Program");
    logger1.logMessage(LogType.ERROR, "Encountered a Error");
    logger1.logMessage(LogType.DEBUG, "Printing Json");
    logger1.logMessage(LogType.DEBUG, "Printing Values");
  }
}
