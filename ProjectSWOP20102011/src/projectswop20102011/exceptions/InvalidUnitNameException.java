package projectswop20102011.exceptions;

/**
 * An exception that will be used when the name of a unit is wrong.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidUnitNameException extends Exception{

    private static final long serialVersionUID = 1L;

    public InvalidUnitNameException(String message) {
        super(message);
    }

}
