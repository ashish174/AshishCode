package designpattern.behavioural.statepattern.documentLifecycle;


/**
 * Concrete State representing the Moderation state.
 */
public class ModerationState implements State {
    @Override
    public void handleRequest(DocumentContext context) {
        System.out.println("Moderation: Approved. Moving to Published.");
        context.setState(new PublishedState());
    }

    @Override
    public String getStateName() {
        return "Moderation";
    }
}
