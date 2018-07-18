package designpattern.behavioural.chainofresponsibilitypattern;

/*
It is used to achieve loose coupling in software design where a request from client is passed to a chain of objects to process them.
object in the chain will decide themselves who will be processing the request and whether the request is required to be sent to the next object in the chain or not.
Ex: try with multiple catch block to catch the exception.
If one of the chain not able to process it fully, it sends the request to the next processor in chain to process the remaining request.
If the processor is not able to process anything, it just forwards the same request to the next chain.
Every object in the chain should have reference to the next object in chain to forward the request to, its achieved by java composition.
java.util.logging.Logger#logMessage()
javax.servlet.Filter#doFilter()
*/

public interface DispenseChain {
  void setNextChain(DispenseChain nextChain);
  void dispense(Currency currency);
}
