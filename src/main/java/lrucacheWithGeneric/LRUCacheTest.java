package lrucacheWithGeneric;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class LRUCacheTest {
  static Logger logger = LoggerFactory.getLogger(LRUCacheTest.class);

  public static void main(String[] args) {
    MyLRUCache<String, Integer> myLRUCache = new MyLRUCache<>(3);
    String key;
    while(true){
      System.out.println("Enter Key to search in Cache");
      Scanner scanner = new Scanner(System.in);
      key = scanner.nextLine();
      myLRUCache.getValue(key);
    }
  }
}
