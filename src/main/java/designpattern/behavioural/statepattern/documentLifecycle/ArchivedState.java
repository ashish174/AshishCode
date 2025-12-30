package designpattern.behavioural.statepattern.documentLifecycle;

/**
 * Concrete State representing the Archived state.
 */
public class ArchivedState implements State {
    @Override
    public void handleRequest(DocumentContext context) {
        System.out.println("Archived: Document can't be changed further.");
    }

    @Override
    public String getStateName() {
        return "Archived";
    }
}
