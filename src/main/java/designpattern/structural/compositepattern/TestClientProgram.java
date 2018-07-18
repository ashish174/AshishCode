package designpattern.structural.compositepattern;


import designpattern.creational.prototype.Employees;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class TestClientProgram {
  private static Logger logger = LogManager.getLogger(TestClientProgram.class);
  public static void main(String[] args) {
    Shape triangle = new Triangle();
    Shape circle = new Circle();
    Shape triangle1 = new Triangle();
    Drawing mydrawing = new Drawing();
    mydrawing.add(triangle);
    mydrawing.add(circle);
    mydrawing.add(triangle1);

    mydrawing.draw("Blue");

    mydrawing.remove(triangle);

    mydrawing.draw("Green");

    mydrawing.clear();

  }
}
