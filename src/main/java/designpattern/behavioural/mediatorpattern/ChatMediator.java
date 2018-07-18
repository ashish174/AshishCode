package designpattern.behavioural.mediatorpattern;

/*
Mediator design pattern is used to provide a centralized communication medium between different objects in a system.
Mediator pattern focuses on provide a mediator between objects for communication and help in implementing lose-coupling between objects.
Mediator design pattern is very helpful in an enterprise application where multiple objects are interacting with each other.
Ex: Air traffic controller is a mediator which works as a mediator for communication between different flights.
The system objects that communicate each other are called Colleagues
An interface or abstract class as mediator which define the contract for communication
and then several concrete implementation of the mediator
Ex: java.util.Timer class scheduleXXX() methods
Java Concurrency Executor execute() method.
java.lang.reflect.Method invoke() method.
Java Message Service (JMS) uses Mediator pattern along with Observer pattern to allow applications to subscribe and publish data to other applications.
*/

public interface ChatMediator {
  public void sendMessage(String msg, User user);
  public void addUser(User user);
}
