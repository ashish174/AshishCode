package designpattern.creational.singletonpattern;

/**
 * A class is loaded and initialized only when it is first actively used.
 * Holder class is loaded only when Holder.Instance is invoked
 *
 */
public class LazyStaticHolderSingleton {

    private LazyStaticHolderSingleton(){}
    public static LazyStaticHolderSingleton getInstance(){
        return Holder.Instance;
    }

  /**
   * Class initialization (THREAD-SAFE) : JVM guarantees this runs exactly once.
   * JVM locks the class initialization.
   * No other thread can run it at the same time
   */
  private static class Holder {
        private static final LazyStaticHolderSingleton Instance = new LazyStaticHolderSingleton();
    }

}
