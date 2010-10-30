package projectswop20102011.exceptions;

/**
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class InvalidSpeedException extends Exception{

	/**
     * Variable registering the serivalVersionUID of this InvalidSpeedException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of an InvalidSpeedException with no detail message.
     */
	public InvalidSpeedException(){
		super();
	}

    /**
     * Creates a new InvalidSpeedException with a specified message.
     * @param message
	 *		A message explaining why this Exception has been thrown.
     */
    public InvalidSpeedException(String message) {
        super(message);
    }

}
