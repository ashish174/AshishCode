package designpattern.creational.singletonpattern;
/*
Singleton ensures that only one instance of the class
exists in the java virtual machine.
*/
public class SingletonWithDoubleLock {
  private static volatile SingletonWithDoubleLock singletonInstance;

  private SingletonWithDoubleLock() {
  }

  public static SingletonWithDoubleLock getSingleTonInstance(){
    if(singletonInstance==null){
      synchronized (SingletonWithDoubleLock.class){
        if(singletonInstance==null){
          singletonInstance = new SingletonWithDoubleLock();
        }
      }
    }
    return singletonInstance;
  }
}
