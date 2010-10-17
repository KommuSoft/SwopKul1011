package projectswop20102011;

/**
 * Thrown when an application attempts to use an invalid phone number.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class InvalidPhoneNumberException extends RuntimeException{
    /**
     * Variable registering the serialVersionUID of this InvalidPhoneNumberException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an InvalidPhoneNumberException with no detail message.
     */
    public InvalidPhoneNumberException(){
        super();
    }

    /**
     * Constructs an InvalidPhoneNumbersException with the specified detail message.
     * @param message The detail message.
     */
    public InvalidPhoneNumberException(String message){
        super(message);
    }
}
