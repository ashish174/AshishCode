package designpattern.behavioural.statepattern;

/**
 * The State Design Pattern allows an object to alter its behavior
 * when its internal state changes. The object will appear to change its class.
 *
 * Instead of using:
   * if (state == A) doA();
   * else if (state == B) doB();
 *
 * We prefer using
    * currentState.handle();
 * and each state implements handle() differently.
 *
 *  Rather than using many conditional statements, the behavior
 * is encapsulated within state objects, allowing objects to
 * change their behavior by changing their state.
 *
 *  * The State interface encapsulates behavior associated with a particular
 *  * state of the TV. Each Concrete State implements this interface.
 *
 *  Other Examples:
 *  - Vending Machine
 *
 */
public interface State {
  /**
   * Performs the state's action and possibly transitions to other states.
   * @param context The context object (TV) whose state can be changed.
   * @param command The command issued by the user.
   */
  void handleRequest(TVContext context, String command);

  /**
   * Returns the name of the state for logging and debugging.
   */
  String getStateName();
}
