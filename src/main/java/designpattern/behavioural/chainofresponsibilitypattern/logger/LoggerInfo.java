package designpattern.behavioural.chainofresponsibilitypattern.logger;

public class LoggerInfo implements Logger {
  private Logger nextLogger;

  @Override
  public void setNextChain(Logger logger) {
    this.nextLogger = logger;
  }

  @Override
  public void logMessage(LogType logType, String message) {
    if(logType==LogType.INFO){
      System.out.println("Logger.INFO : "+message);
    } else {
      nextLogger.logMessage(logType, message);
    }
  }
}
