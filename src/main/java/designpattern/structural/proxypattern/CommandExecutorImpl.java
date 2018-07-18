package designpattern.structural.proxypattern;

//This class allows all to execute any command
public class CommandExecutorImpl implements CommandExecutor{
  @Override
  public void runCommand(String cmd) throws Exception {
    //some heavy implementation
    Runtime.getRuntime().exec(cmd);
    System.out.println(cmd+ " : Command Executed ");
  }
}
