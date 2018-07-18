package designpattern.behavioural.interpretorpattern;


import designpattern.structural.proxypattern.CommandExecutor;
import designpattern.structural.proxypattern.CommandExecutorProxy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/*
This is used to defines a grammatical representation for a language
and provides an interpreter to deal with this grammar.
Ex: java compiler that interprets the java source code into byte code
Ex: Google Translator where the input can be in any language and we can get the output interpreted in another language.
To implement interpreter pattern, we need to create Interpreter context engine that will do the interpretation work.
Then we need to create different Expression implementations that will consume the functionalities provided by the interpreter context.
Finally we need to create the client that will take the input from user and decide which Expression to use and then generate output for the user.
*/

public class InterpreterClient {
  private static Logger logger = LogManager.getLogger(InterpreterClient.class);
  InterpreterContext interpreterContext;

  public InterpreterClient(InterpreterContext interpreterContext) {
    this.interpreterContext = interpreterContext;
  }

  public static void main(String[] args) {



  }
}
