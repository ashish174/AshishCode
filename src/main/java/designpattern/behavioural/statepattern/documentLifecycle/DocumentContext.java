package designpattern.behavioural.statepattern.documentLifecycle;

/**
 * A Context class that represent a document.
 *
 * Context class that has a current State.
 * Delegates state-specific behavior to its current State.
 *
 * Your context object will have a State attribute, and context will call state attribute to get the behaviour
 */
public class DocumentContext {
    private State currentState;

    /**
     * Sets the current state of the document.
     *
     * @param state the new state to transition to.
     */
    public void setState(State state) {
        this.currentState = state;
        System.out.println("Document state changed to: " + state.getStateName());
    }

    /**
     * Gets the current State object.
     */
    public State getState() {
        return currentState;
    }

    /**
     * Requests the current state to handle the action.
     */
    public void handleRequest() {
        currentState.handleRequest(this);
    }
}

