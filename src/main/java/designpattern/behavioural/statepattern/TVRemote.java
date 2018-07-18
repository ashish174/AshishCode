package designpattern.behavioural.statepattern;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/*
It is used when an Object change it’s behavior based on it’s internal state.
If internal state of Object changes -> behaviour of Object changes
If we have to change the behavior of an object based on it’s state,
    we can have a state variable in the Object and use if-else condition block to perform different actions based on the state.
Object has a  Context Object. Context Object has a State.
State pattern is used to provide a systematic and lose-coupled way to achieve this through Context and State implementations.
Context is the class that has a State reference to one of the concrete implementations of the State.
Context forwards the request to the state object for processing.
*/

public class TVRemote {
  private static Logger logger = LogManager.getLogger(TVRemote.class);
  public static void main(String[] args) {

    TVContext tvContext = new TVContext();
    State tvStartState = new TVStartState();
    State tvStopState = new TVStopState();

    tvContext.setTvState(tvStartState);
    tvContext.doAction();

    tvContext.setTvState(tvStopState);
    tvContext.doAction();
  }
}
