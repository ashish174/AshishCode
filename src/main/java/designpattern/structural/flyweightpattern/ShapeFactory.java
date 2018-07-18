package designpattern.structural.flyweightpattern;

import java.util.HashMap;
import java.util.Map;

/*
This is used when we need to create a lot of Objects of a class.
Since every object consumes memory space that can be crucial for low memory devices, such as mobile devices
Flyweight design pattern can be applied to reduce the load on memory by sharing objects.
Ex: StringPool implementation
we need to divide Object property into intrinsic and extrinsic properties.
Intrinsic properties make the Object unique whereas extrinsic properties are set by client code and used to perform different operations.
For example, an Object Circle can have extrinsic properties such as color and width.
Flyweight factory returns the shared objects.
flyweight pattern can be used for Objects that takes a lot of time while instantiated.

Flyweight factory keep a map of Objects in the factory that should not be accessible by client application.
When client want to get an instance of Object, it should be returned from the HashMap,
if not found then create a new Object and put in the Map and then return it.
*/
public class ShapeFactory {
  //Java Composition
  private static final Map<ShapeType, Shape> shapes = new HashMap<>();

  //Factory Pattern
  public static Shape getShape(ShapeType type){
    Shape shape = shapes.get(type);
    if(shape==null){
      if (type.equals(ShapeType.OVAL_FILL)) {
        shape = new Oval(true);
      } else if(type.equals(ShapeType.OVAL_NOFILL)){
        shape = new Oval(false);
      } else if(type.equals(ShapeType.LINE)){
        shape = new Line();
      }
      shapes.put(type, shape);
    }
    return shape;
  }


}
