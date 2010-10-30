package projectswop20102011.exceptions;

/**
 * An exception that will be thrown when the name of a hospital is wrong.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidHospitalNameException extends Exception{

	/**
     * Variable registering the serivalVersionUID of this InvalidHospitalNameException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of an InvalidHospitalNameException with no detail message.
     */
	public InvalidHospitalNameException(){
		super();
	}
	
    /**
     * Creates a new instance of an InvalidHospitalNameException with a specified message.
     * @param message
	 *		A message explaining the cause of the exception.
     */
    public InvalidHospitalNameException(String message) {
        super(message);
    }
}
