package designpattern.behavioural.strategypattern;

/*
also known as Policy Pattern.
Strategy pattern is used when we have multiple algorithm for a specific task
and client decides the actual implementation to be used at runtime.
We defines multiple algorithms
and let client application pass the algorithm to be used as a parameter.
Ex: Collections.sort() method that takes Comparator parameter.
Arrays.sort()
Based on the different implementations of Comparator interfaces, the Objects are getting sorted in different ways.
*/
public interface PaymentStrategy {
  public void pay(int amount);
}
