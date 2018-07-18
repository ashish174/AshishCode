package designpattern.structural.proxypattern;


/*
Proxy design pattern common uses are to control access or to provide a wrapper implementation for better performance.

To Give controlled Access of a program/class to other program/class.
It provide a surrogate or placeholder for another object to control access to it.
Proxy pattern is used when we want to provide controlled access of a functionality.
Suppose a class can run various commands in system.
And we want to give access to few commands of this class and not all(controlled access) to Client Program.
A proxy class can be created to provide controlled access of this class/program.

CommandExecutor can execute any command even delete command.
Proxy class provide only admin users to have full access of above class,
if the user is not admin then only limited commands will be allowed.
Here is our very simple proxy class implementation.
Ex: Java RMI package
*/

public class CommandExecutorProxy implements CommandExecutor{
  private boolean isAdmin;
  private CommandExecutor executor = new CommandExecutorImpl();

  public CommandExecutorProxy(String username, String password) {
    if((username.equals("Ashish")) && password.equals("Welcome1")){
      isAdmin = true;
      //executor = new CommandExecutorImpl();
    }
  }

  @Override
  public void runCommand(String cmd) throws Exception {
    if(isAdmin){
      executor.runCommand(cmd);
    }
    else {
      if(cmd.trim().startsWith("rm")){
        throw new Exception("rm Command not allowed for non admin users");
      } else{
        executor.runCommand(cmd);
      }
    }
  }
}
