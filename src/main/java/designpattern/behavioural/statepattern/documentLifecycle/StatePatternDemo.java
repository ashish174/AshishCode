package designpattern.behavioural.statepattern.documentLifecycle;

/**
 * Demonstrates the State Pattern with a document lifecycle.
 */
public class StatePatternDemo {
    public static void main(String[] args) {
        DocumentContext doc = new DocumentContext();
        doc.setState(new DraftState()); // initial state

        doc.handleRequest(); // Moves from Draft -> Moderation
        doc.handleRequest(); // Moves from Moderation -> Published
        doc.handleRequest(); // Moves from Published -> Archived
        doc.handleRequest(); // Archived: can't change further
    }
}
