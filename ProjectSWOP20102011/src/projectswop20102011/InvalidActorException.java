package projectswop20102011;

/**
 * An exception class activated when invalid actors are handled (for instance the name of the actor is unknown by the system)
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class InvalidActorException extends Exception {
    
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of <code>InvalidActorException</code> without detail message.
     */
    public InvalidActorException() {
    }


    /**
     * Constructs an instance of <code>InvalidActorException</code> with the specified detail message.
     * @param message the detail message.
     */
    public InvalidActorException(String message) {
        super(message);
    }
}
