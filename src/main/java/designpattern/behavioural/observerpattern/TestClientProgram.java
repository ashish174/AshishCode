package designpattern.behavioural.observerpattern;


import designpattern.behavioural.templatemethodpattern.GlassHouse;
import designpattern.behavioural.templatemethodpattern.HouseTemplate;
import designpattern.behavioural.templatemethodpattern.WoodenHouse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/*
Also called as publish-subscribe pattern.
when you are interested in the state of an object and want to get notified whenever there is any change.
Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
Observer - object that watch on the state of another object
Subject - object that is being watched
Subject contains a list of observers to notify. it provide method to add/remove observers. A method to notify all either thru push/pull.
A boolean variable to keep track of the change, so that if there is no update and somebody calls notifyObservers() method, it doesn’t send false notifications to the observers.

Java provides inbuilt platform through  java.util.Observable class and java.util.Observer interface. But that is too sinple for our use.
JMS uses Observer pattern along with Mediator pattern to allow applications to subscribe and publish data to other applications.
MVC frameworks also use Observer pattern where Model is the Subject and Views are observers that can register to get notified of any change to the model.
Model changes and notify the Views to update itself.
java.util.EventListener in Swing
javax.servlet.http.HttpSessionBindingListener
javax.servlet.http.HttpSessionAttributeListener
*/

public class TestClientProgram {
  private static Logger logger = LogManager.getLogger(TestClientProgram.class);
  public static void main(String[] args) {
    MyTopic myTopic = new MyTopic();

    Observer subscriber1 = new MyTopicSubscriber("subscriber1");
    Observer subscriber2 = new MyTopicSubscriber("subscriber2");
    Observer subscriber3 = new MyTopicSubscriber("subscriber3");

    myTopic.register(subscriber1);
    myTopic.register(subscriber2);
    myTopic.register(subscriber3);

    subscriber1.setSubject(myTopic);
    subscriber2.setSubject(myTopic);
    subscriber3.setSubject(myTopic);

    myTopic.postMessage("Hiii Buddies");

    myTopic.postMessage("Today it ll rain");


  }
}
