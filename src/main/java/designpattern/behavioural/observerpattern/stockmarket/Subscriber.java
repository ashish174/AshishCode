package designpattern.behavioural.observerpattern.stockmarket;

import designpattern.behavioural.observerpattern.Observer;
import designpattern.behavioural.observerpattern.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Subscriber implements Observer {
  Logger logger = LoggerFactory.getLogger(Subscriber.class);
  String name;
  String accountId;
  List<Subject> stocks;
  Object lock = new Object();
  //@Override
  public void updatePrice(String stockName) {

  }

  //@Override
  public void addStock(Subject stock) {
    synchronized (lock){
      if(Objects.isNull(stocks)){
        stocks = new ArrayList<>();
      }
      if(!stocks.contains(stock)){
        stocks.add(stock);
        logger.info("Added Stock {} for Subscriber {}", stock, name);
      } else {
        logger.warn("Stock {} already exist for Subscriber {}", stock, name);
      }
    }
  }

  @Override
  public void update() {

  }

  @Override
  public void setSubject(Subject subject) {

  }
}
