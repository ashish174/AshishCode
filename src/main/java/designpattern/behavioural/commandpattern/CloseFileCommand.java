package designpattern.behavioural.commandpattern;

public class CloseFileCommand implements Command{
  private FileSystemReceiver fileSystemReceiver;

  public CloseFileCommand(FileSystemReceiver fileSystemReceiver) {
    this.fileSystemReceiver = fileSystemReceiver;
  }

  @Override
  public void execute() {
    //close command is forwarding request to closeFile method
    this.fileSystemReceiver.closeFile();
  }
}
