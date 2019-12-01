package exceptions;

import java.io.File;
import java.io.FileNotFoundException;

public class TestException {

  void doSomething() throws FileNotFoundException {
    File f = new File("/Documents/ashish");
    if (!f.exists())
      throw new FileNotFoundException("File Name is not Correct");

  }

  void testException() {
    try {
      doSomething();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
