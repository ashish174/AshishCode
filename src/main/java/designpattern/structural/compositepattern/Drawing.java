package designpattern.structural.compositepattern;

/*
This  is used when we have to represent a part-whole hierarchy.
Composite Pattern consists of following objects:-
Base Component (Shape)-  This is the interface/abstract class for all objects in the composition, client program uses base component to work with the objects in the composition.
Leaf (Circle, Triangle)- Defines the behaviour for the elements in the composition. It  implements base component.
 It doesn’t have references to other Components.
Composite -  It consists of leaf elements and implements the operations in base component.
We should provide some helper methods to add or delete leafs from the group.
We can also provide a method to remove all the elements from the group.
Composite pattern should be applied only when the group of objects should behave as the single object.
Composite design pattern can be used to create a tree like structure.
Ex: java.awt.Container#add(Component) in java swing
*/

import java.util.ArrayList;
import java.util.List;

public class Drawing implements Shape{

  //collection of Shapes
  private List<Shape> shapes = new ArrayList<>();

  @Override
  public void draw(String colourToUse) {
    for(Shape shape : shapes){
      shape.draw(colourToUse);
    }
  }

  public void add(Shape shape){
    shapes.add(shape);
  }

  public void remove(Shape shape){
    shapes.remove(shape);
  }

  public void clear(){
    System.out.println("Clearing all the Shapes from Drawing ");
    this.shapes.clear();
  }
}
