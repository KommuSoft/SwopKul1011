package projectswop20102011.exceptions;

/**
 * An exception that will be used when the name of a unit is wrong.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidUnitNameException extends Exception{

    /**
     * Variable registering the serivalVersionUID of this InvalidUnitNameException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an InvalidUnitNameException with no detail message.
     */
	public InvalidUnitNameException(){
		super();
	}

    /**
     * Constructs an InvalidUnitNameException with the specified detail message.
     * @param message
     *      The detail message.
     */
    public InvalidUnitNameException(String message) {
        super(message);
    }

}
