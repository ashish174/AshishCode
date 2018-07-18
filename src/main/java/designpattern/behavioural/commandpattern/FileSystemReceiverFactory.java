package designpattern.behavioural.commandpattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class FileSystemReceiverFactory {
  private static Logger logger = LogManager.getLogger(FileSystemReceiverFactory.class);

  static FileSystemReceiver fileSystemReceiver;
  public static FileSystemReceiver getFileSystem(FileType fileType){
    logger.info("Returning File Receiver for "+fileType.name()+" OS");
    switch (fileType){
      case UNIX:
        fileSystemReceiver = new UnixFileSystemReceiver();
        break;
      case WINDOWS:
        fileSystemReceiver = new WindowFileSystemReceiver();
        break;
      default:
        logger.error("Invalid Type");
    }
    return fileSystemReceiver;
  }


}
