package designpattern.behavioural.chainofresponsibilitypattern.logger;

public class LoggerError implements Logger {
  private Logger nextLogger;

  @Override
  public void setNextChain(Logger logger) {
    this.nextLogger = logger;
  }

  @Override
  public void logMessage(LogType logType, String message) {
    if(logType==LogType.ERROR){
      System.out.println("Logger.ERROR : "+message);
    } else {
      throw new RuntimeException("Invalid logger Type");
    }
  }
}
