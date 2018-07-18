package designpattern.behavioural.observerpattern;

import java.util.ArrayList;
import java.util.List;

public class MyTopic implements Subject {
  private List<Observer> observers;
  private String message;
  private boolean changed;
  private final Object MUTEX = new Object();

  public MyTopic() {
    observers = new ArrayList<>();
  }

  @Override
  public void register(Observer observer) {
    if(observer==null){
      throw new NullPointerException("Null Observer");
    }
    synchronized (MUTEX){
      if(!observers.contains(observer)){
        observers.add(observer);
      }
    }
  }

  @Override
  public void unregister(Observer observer) {
    synchronized (MUTEX){
      observers.remove(observer);
    }
  }

  @Override
  public void notifyObservers() {
    List<Observer> observersLocal = null;
    //synchronization is used to make sure any observer registered after message is received is not notified
    synchronized (MUTEX){
      if(!changed)
        return;
      observersLocal = new ArrayList<>(this.observers);
      this.changed=false;
    }
    for(Observer observer : observersLocal){
      observer.update();
    }

  }

  @Override
  public String getUpdate(Observer observer) {
    return this.message;
  }

  //method to post message to the topic
  public void postMessage(String message){
    System.out.println("Message Posted to Topic : "+message);
    this.message = message;
    this.changed = true;
    notifyObservers();
  }
}
