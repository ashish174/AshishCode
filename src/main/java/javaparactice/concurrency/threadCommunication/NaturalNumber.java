package javaparactice.concurrency.threadCommunication;

public class NaturalNumber {
  Integer numLock;
  public static void main(String[] args) {
    Integer numLock = new Integer(1);
    IsEven isEven = new IsEven(true);
    Thread evenThread = new Thread(new EvenThread(isEven), "EvenThread");
    Thread oddThread = new Thread(new OddThread(isEven), "OddThread");

    evenThread.start();
    oddThread.start();



  }
}
