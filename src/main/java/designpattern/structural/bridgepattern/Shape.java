package designpattern.structural.bridgepattern;

/*
Prefer Composition over inheritance.
When we have interface hierarchies in both interfaces as well as implementations,
then bridge design pattern is used to decouple the interfaces from implementation and hiding the implementation details from the client programs.
It Decouple an abstraction from its implementation so that the two can vary independently.
Bridge design pattern can be used when both abstraction and implementation can have different hierarchies independently
and we want to hide the implementation from the client application.
Progressively add functionality while separating out major diff using abstract classes
*/
public abstract class Shape {
  //Composition
  protected Color color;

  public Shape(Color color) {
    this.color = color;
  }

  abstract public void applyColor();
}
