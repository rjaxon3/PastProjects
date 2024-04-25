/**
 * @author Rhea Jaxon
 * @version 1
 */
public class InvalidPartyMemberException extends RuntimeException {
    /**
     * Constructor taking in message.
     * @param message message of exception
     */
    public InvalidPartyMemberException(String message) {
        super(message);
    }
    /**
     * Constructor taking in no parameters.
     */
    public InvalidPartyMemberException() {
        super("Invalid party member!");
    }
}