package designpattern.behavioural.chainofresponsibilitypattern.logger;

public interface Logger {
  void setNextChain(Logger logger);
  void logMessage(LogType logType, String message);
}
