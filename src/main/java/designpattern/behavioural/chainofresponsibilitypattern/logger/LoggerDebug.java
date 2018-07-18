package designpattern.behavioural.chainofresponsibilitypattern.logger;

public class LoggerDebug implements Logger {
  private Logger nextLogger;

  @Override
  public void setNextChain(Logger logger) {
    this.nextLogger = logger;
  }

  @Override
  public void logMessage(LogType logType, String message) {
    if(logType==LogType.DEBUG){
      System.out.println("Logger.DEBUG : "+message);
    } else {
      nextLogger.logMessage(logType, message);
    }
  }
}
