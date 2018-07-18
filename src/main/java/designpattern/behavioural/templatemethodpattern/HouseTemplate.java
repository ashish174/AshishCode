package designpattern.behavioural.templatemethodpattern;
/*
It is used to create a method stub and
deferring some of the steps of implementation to the subclasses.
Template method defines the steps to execute an algorithm and it can provide default implementation also.
Subclass gives their specific implementation or override methods.
Template method should consists of certain steps whose order is fixed and for some of the methods, implementation differs from base class to subclass.
Template method should be final.
Ex:The steps need to be performed to build a house are – building foundation, building pillars, building walls and windows.
The important point is that the we can’t change the order of execution
*/
public abstract class HouseTemplate {

  //template method defining step order, marked final so subclasses can't override
  public final void buildHouse(){
    buildFoundation();
    buildPillars();
    buildWalls();
    buildWindows();
    System.out.println("House is built.");
  }

  //default final implementation
  public final void buildFoundation() {
    System.out.println("Building Founadtion with cement, iron, sand");
  }

  public abstract void buildPillars();
  public abstract void buildWalls();

  //default implementation
  public void buildWindows() {
    System.out.println("Building Glass Windows");
  }
}
