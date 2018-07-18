package designpattern.behavioural.commandpattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class WindowFileSystemReceiver implements FileSystemReceiver{
  private static Logger logger = LogManager.getLogger(WindowFileSystemReceiver.class);

  @Override
  public void openFile() {
    logger.info("Opening File in Windows");
  }

  @Override
  public void writeFile() {
    logger.info("Writing File in Windows");
  }

  @Override
  public void closeFile() {
    logger.info("Closing file in Windows");
  }
}
