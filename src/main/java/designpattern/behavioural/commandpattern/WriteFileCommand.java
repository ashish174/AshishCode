package designpattern.behavioural.commandpattern;

public class WriteFileCommand implements Command{
  private FileSystemReceiver fileSystemReceiver;

  public WriteFileCommand(FileSystemReceiver fileSystemReceiver) {
    this.fileSystemReceiver = fileSystemReceiver;
  }

  @Override
  public void execute() {
    //write command is forwarding request to writeFile method
    this.fileSystemReceiver.writeFile();
  }
}
