package lrucacheWithGeneric;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MyLRUCache<K,V> {
  //hold cache of size 10
  //Name(String) -> phonenumber(Integer)
  Logger logger = LoggerFactory.getLogger(MyLRUCache.class);
  MyQueue cacheQueue;
  Map<K, Node<K,V>> cacheMap;
  int cacheThresholdSize;
  int cacheSize;

  public MyLRUCache(int size) {
    cacheThresholdSize = size;
    cacheQueue = new MyQueue();
    cacheMap = new HashMap<>(cacheThresholdSize, 1);
    logger.info("Initializing Cache of Threshold Size {}", cacheThresholdSize);

  }

  public MyLRUCache() {
    cacheThresholdSize = 10;
    cacheQueue = new MyQueue();
    cacheMap = new HashMap<>(cacheThresholdSize, 1);
    logger.info("Initializing Cache of Threshold Size {}", cacheThresholdSize);

  }

  V getValue(K key) {
    V value;
    logger.info("Get for Key {}", key);
    //Hit
    if (cacheMap.containsKey(key)) {
      logger.info("Cache Hit");
      Node queueNode = cacheMap.get(key);
      queueNode = cacheQueue.remove(queueNode);
      cacheQueue.insert(new Node(queueNode.getKey(), queueNode.getValue()));
      value = (V) queueNode.getValue();
    } //Miss
    else {
      logger.info("Cache Miss");
      value = (V)((Integer)new Random().nextInt(10000000));
      setValue(key, value);
    }
    logger.info("Get Cache Value for Key {} is - {}", key, value);
    logger.info("**********cache Detail************ ");
    logger.info("cacheMap Size {}", cacheMap.size());
    cacheQueue.printQueue();
    return value;
  }

  void setValue(K key, V value) {
    logger.info("Set Cache Key Value {} - {}", key, value);
    if (isCacheOverFlowed()) {
      Node deletedQueueNode = cacheQueue.remove();
      cacheMap.remove(deletedQueueNode.getKey());
      logger.info("LRU Cache Replacement: Key - Value Replaced {} - {}", deletedQueueNode.getKey(), deletedQueueNode.getValue());
    }
    Node insertedQueueNode = cacheQueue.insert(new Node(key, value));
    cacheMap.put(key, insertedQueueNode);
  }

  boolean isCacheOverFlowed() {
    if (cacheMap.size() < cacheThresholdSize)
      return false;
    logger.info("Cache OverFlowed. Need Replacement");
    return true;

  }


}
