package projectswop20102011.exceptions;

/**
 * An exception clas used when a DispatchPolicy object is invalid.
 * @author janvanonsem
 */
public class InvalidDispatchPolicyException extends Exception {

    /**
     * Creates a new instance of <code>InvalidDispatchPolicyException</code> without detail message.
     */
    public InvalidDispatchPolicyException() {
    }

    /**
     * Constructs an instance of <code>InvalidDispatchPolicyException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidDispatchPolicyException(String msg) {
        super(msg);
    }
}
