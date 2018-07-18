package designpattern.structural.proxypattern;


import designpattern.creational.prototype.Employees;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class TestClientProgram {
  private static Logger logger = LogManager.getLogger(TestClientProgram.class);
  public static void main(String[] args) {

    CommandExecutor executor = new CommandExecutorProxy("Pankaj", "Chutiya1");
    try {
      executor.runCommand("ls");
      executor.runCommand("rm -rf abc.pdf");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
