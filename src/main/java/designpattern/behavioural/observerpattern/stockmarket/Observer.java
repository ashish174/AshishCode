package designpattern.behavioural.observerpattern.stockmarket;

public interface Observer {
  void updatePrice(String stockName);
  void addStock(Subject stock);
}
