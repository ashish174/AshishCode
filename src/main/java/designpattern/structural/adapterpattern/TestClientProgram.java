package designpattern.structural.adapterpattern;


import designpattern.creational.prototype.Employees;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class TestClientProgram {
  private static Logger logger = LogManager.getLogger(TestClientProgram.class);
  public static void main(String[] args) {
    SocketAdapter socketClassAdapter = new SocketClassAdapterImpl();
    logger.info("socketClassAdapter 3V "+socketClassAdapter.get3Volt());
    logger.info("socketClassAdapter 12V "+socketClassAdapter.get12Volt());
    logger.info("socketClassAdapter 120V "+socketClassAdapter.get120Volt());

    SocketAdapter socketObjectAdapter = new SocketObjectAdapterImpl();
    logger.info("socketObjectAdapter 3V "+socketObjectAdapter.get3Volt());
    logger.info("socketObjectAdapter 12V "+socketObjectAdapter.get12Volt());
    logger.info("socketObjectAdapter 120V "+socketObjectAdapter.get120Volt());



  }
}
