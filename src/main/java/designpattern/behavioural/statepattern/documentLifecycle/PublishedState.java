package designpattern.behavioural.statepattern.documentLifecycle;

/**
 * Concrete State representing the Published state.
 */
public class PublishedState implements State {
    @Override
    public void handleRequest(DocumentContext context) {
        System.out.println("Published: Archiving document.");
        context.setState(new ArchivedState());
    }

    @Override
    public String getStateName() {
        return "Published";
    }
}
