package designpattern.behavioural.commandpattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UnixFileSystemReceiver implements FileSystemReceiver{
  private static Logger logger = LogManager.getLogger(UnixFileSystemReceiver.class);

  @Override
  public void openFile() {
    logger.info("Opening File in Unix");
  }

  @Override
  public void writeFile() {
    logger.info("Writing File in Unix");
  }

  @Override
  public void closeFile() {
    logger.info("Closing file in Unix");
  }
}
