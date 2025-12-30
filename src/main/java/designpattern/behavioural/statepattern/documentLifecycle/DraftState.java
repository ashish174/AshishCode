package designpattern.behavioural.statepattern.documentLifecycle;


/**
 * Concrete State representing the Draft state.
 */
public class DraftState implements State {
    @Override
    public void handleRequest(DocumentContext context) {
        System.out.println("Draft: Ready for review. Moving to Moderation.");
        context.setState(new ModerationState());
    }

    @Override
    public String getStateName() {
        return "Draft";
    }
}

