package designpattern.behavioural.observerpattern.stockmarket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Stock implements Subject {
  Logger logger = LoggerFactory.getLogger(Stock.class);
  String name;
  String company;
  String desc;
  double price;
  boolean isChange;
  List<Observer> subscribers;
  Object lock = new Object();
  @Override
  public void subscribe(Observer observer) {
    synchronized (lock){
      if(Objects.isNull(subscribers)){
        subscribers = new ArrayList<>();
      }
      if(!subscribers.contains(observer)){
        subscribers.add(observer);
        logger.info("Added Observer {} for Stock {}", observer, name);
      } else {
        logger.warn("Observer already exist {} for Stock {}", observer, name);
      }
    }
  }

  @Override
  public void unsubscribe(Observer observer) {
    synchronized (lock){
      if(Objects.nonNull(subscribers) && subscribers.contains(observer)){
        subscribers.remove(observer);
        logger.info("Observer Removed {} for Stock {}", observer, name);
      } else {
        logger.warn("Observer {} Not found for Stock {}", observer, name);
      }
    }
  }

  @Override
  public void notifyAllSubscribers() {
    List<Observer> subscribersToNotify;
    synchronized (lock){
      if(isChange){
        logger.info("Prices Changes. Notifying observers");
      }
      subscribersToNotify = new ArrayList<>(this.subscribers);
    }
    for(Observer observer : subscribersToNotify){
      observer.updatePrice(this.name);
    }
  }

  @Override
  public void updatePrice() {
    synchronized (lock){
      price = new Random(1000).nextDouble();
      isChange = true;
    }
    notifyAllSubscribers();
  }

  @Override
  public void getPrice(Observer observer) {
    //observer
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Stock stock = (Stock) o;
    return Objects.equals(name, stock.name);
  }

  @Override
  public int hashCode() {

    return Objects.hash(name);
  }
}
