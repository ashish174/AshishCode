package memoryLeak;

import java.sql.Connection;
import java.sql.DriverManager;


/*
Eliminate obsolete object references.
Because memory leaks typically do not manifest themselves as obvious failures, they may remain present in a system for years.
    They are typically discovered only as a result of careful code inspection
    or with the aid of a debugging tool known as a heap profiler.
    Therefore, it is very desirable to learn to anticipate problems like this before they occur and prevent them from happening.

    Generally speaking, whenever a class manages its own memory, the programmer should be alert for memory leaks.
    Whenever an element is freed, any object references contained in the element should be nulled out.

    Another common source of memory leaks is caches.
    Once you put an object reference into a cache, it’s easy to forget that it’s there and leave it in the cache long after it becomes irrelevant.
    There are several solutions to this problem.
    If you’re lucky enough to implement a cache for which an entry is relevant exactly so long as there are references to its key outside of the cache, represent the cache as a WeakHashMap;
    entries will be removed automatically after they become obsolete. Remember that WeakHashMap is useful only if the desired lifetime of cache entries is determined by external references to the key, not the value.
*/



public class ReleaseConnection {
  //What if exception occur then connection is never closed
  //or if Reader/Writer stream is opened & never closed
  void releaseConnection() {

    try {
      Connection con = DriverManager.getConnection("");
      // Some Sample Code
      con.close();
    } catch (Exception ex) {
    }
  }
}
