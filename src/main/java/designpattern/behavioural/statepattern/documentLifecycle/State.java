package designpattern.behavioural.statepattern.documentLifecycle;

/**
 *
 * Example: Let's use a Document that can be in
 * various states: Draft, Moderation, Published.
 * The behavior of the document—such as edit(), publish(), archive()
 * —will depend on its current state.
 *
 * The State interface defines the behavior
 * associated with a particular state of the Document.
 *
 * Your context object will have a State attribute, and context will call state attribute to get the behaviour
 * State object concrete implementation knows what will be the
 * behaviour in case of a given state value - Draft, Moderation, Published
 */
public interface State {
    /**
     * Handle request when in this State.
     *
     * @param context The document context (for state transitions)
     */
    void handleRequest(DocumentContext context);

    /**
     * Returns the descriptive name of the state.
     */
    String getStateName();
}
