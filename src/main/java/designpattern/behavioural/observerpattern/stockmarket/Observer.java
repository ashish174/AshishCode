package designpattern.behavioural.observerpattern.stockmarket;

/**
 * The Observer is the object that is interested in the state of the Subject.
 * It registers itself with the Subject to receive notifications.
 */
public interface Observer {
  void updatePrice(String stockName);
  void addStock(Subject stock);
}
