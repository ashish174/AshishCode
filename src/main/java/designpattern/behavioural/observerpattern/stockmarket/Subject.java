package designpattern.behavioural.observerpattern.stockmarket;

/**
 * The Observer pattern is a behavioral design pattern
 * that allows objects to be notified of changes to other objects
 * without having a direct reference to each other.
 *
 * The Subject is the object/topic being observed.
 * It maintains a list of Observers and notifies them when its state changes.
 *
 */
public interface Subject {
  void subscribe(Observer observer);
  void unsubscribe(Observer observer);
  void notifyAllSubscribers();
  void updatePrice();
  void getPrice(Observer observer);
}

