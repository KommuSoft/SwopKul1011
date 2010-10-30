package projectswop20102011.exceptions;

/**
 * An exception that will be thrown for invalid usage of a FireSize or parsing to an unknown FireSize.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class InvalidFireSizeException extends Exception {

	/**
     * Variable registering the serivalVersionUID of this InvalidFireSizeException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of an InvalidFireSizeException with no detail message.
     */
	public InvalidFireSizeException(){
		super();
	}

    /**
     * Creates a new InvalidFireSizeException with a specified message.
     * @param message
	 *		A message explaining why this Exception has been thrown.
     */
    public InvalidFireSizeException(String message) {
        super(message);
    }

}
