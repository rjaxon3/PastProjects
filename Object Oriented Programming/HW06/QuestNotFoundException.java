/**
 * @author Rhea Jaxon
 * @version 1
 */
public class QuestNotFoundException extends Exception {
    /**
     * Constructor taking in message.
     * @param message message of exception
     */
    public QuestNotFoundException(String message) {
        super(message);
    }
    /**
     * Constructor taking in no parameters.
     */
    public QuestNotFoundException() {
        super("Selected Quest Not Found");
    }
}