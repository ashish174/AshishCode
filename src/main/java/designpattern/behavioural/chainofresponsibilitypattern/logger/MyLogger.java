package designpattern.behavioural.chainofresponsibilitypattern.logger;

public class MyLogger {
  private Logger loggerDebug;
  private Logger loggerInfo;
  private Logger loggerError;

  public MyLogger() {
    loggerDebug = new LoggerDebug();
    loggerInfo = new LoggerInfo();
    loggerError = new LoggerError();
  }

  private void createLoggerChain(){
    loggerDebug.setNextChain(loggerInfo);
    loggerInfo.setNextChain(loggerError);
  }

  public Logger getLogger(){
    createLoggerChain();
    return loggerDebug;
  }

  public static Logger getLogger1(){
    MyLogger logger = new MyLogger();
    logger.loggerDebug.setNextChain(logger.loggerInfo);
    logger.loggerInfo.setNextChain(logger.loggerError);
    return logger.loggerDebug;
  }
}
