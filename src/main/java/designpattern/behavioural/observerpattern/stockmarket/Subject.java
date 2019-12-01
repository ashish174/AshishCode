package designpattern.behavioural.observerpattern.stockmarket;

public interface Subject {
  void subscribe(Observer observer);
  void unsubscribe(Observer observer);
  void notifyAllSubscribers();
  void updatePrice();
  void getPrice(Observer observer);
}

