package projectswop20102011.exceptions;

/**
 * An exception that will be thrown when the name of a hospital is wrong.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidHospitalNameException extends Exception{

    private static final long serialVersionUID = 1L;

    public InvalidHospitalNameException(String message) {
        super(message);
    }
}
