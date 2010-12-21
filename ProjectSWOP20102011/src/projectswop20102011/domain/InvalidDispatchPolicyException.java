package projectswop20102011.domain;

/**
 * An exception class used when a DispatchPolicy is invalid.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
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
