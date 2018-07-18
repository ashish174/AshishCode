package designpattern.behavioural.commandpattern;

public class OpenFileCommand implements Command{
  private FileSystemReceiver fileSystemReceiver;

  public OpenFileCommand(FileSystemReceiver fileSystemReceiver) {
    this.fileSystemReceiver = fileSystemReceiver;
  }

  @Override
  public void execute() {
    //open command is forwarding request to openFile method
    this.fileSystemReceiver.openFile();
  }
}
