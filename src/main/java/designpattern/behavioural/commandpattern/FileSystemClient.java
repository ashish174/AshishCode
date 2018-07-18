package designpattern.behavioural.commandpattern;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/*
Command Pattern is used to implement lose coupling in a request-response model.
The request is send to the invoker and invoker pass it to the encapsulated command object.
Command object passes the request to the appropriate method of Receiver to perform the specific action.
client program create the receiver object and then attach it to the Command.
Then it creates the invoker object and attach the command object to perform an action.
Here client is responsible to create the appropriate type of command object.
Client program is also responsible to attach receiver to the command and then command to the invoker class.
Ex: Runnable interface (java.lang.Runnable) and Swing Action (javax.swing.Action) uses command pattern.
*/

public class FileSystemClient {
  private static Logger logger = LogManager.getLogger(FileSystemClient.class);
  public static void main(String[] args) {

    //Creating the receiver object
    FileSystemReceiver fileSystemReceiver = FileSystemReceiverFactory.getFileSystem(FileType.WINDOWS);

    //creating command and associating with receiver
    OpenFileCommand openFileCommand = new OpenFileCommand(fileSystemReceiver);

    //Creating invoker and associating with Command
    FileInvoker fileInvoker = new FileInvoker(openFileCommand);

    //perform action on invoker object
    fileInvoker.execute();

    WriteFileCommand writeFileCommand = new WriteFileCommand(fileSystemReceiver);
    fileInvoker = new FileInvoker(writeFileCommand);
    fileInvoker.execute();

    CloseFileCommand closeFileCommand = new CloseFileCommand(fileSystemReceiver);
    fileInvoker = new FileInvoker(closeFileCommand);
    fileInvoker.execute();

  }
}
