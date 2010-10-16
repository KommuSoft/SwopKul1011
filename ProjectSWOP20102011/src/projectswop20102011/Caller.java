package projectswop20102011;

/**
 * A class of callers.
 *
 * @invar Each caller must have a valid name
 *        |hasValidName()
 * @invar Each caller must have a valid telephoneNumber
 *        |hasValidTelephoneNumber()
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class Caller {

    /**
     * Variable registering the name of this caller
     */
    private final String name;

    /**
     * Variable registering the telephonenumber of this caller
     */
    private final String telephoneNumber;

    /**
     * Initialize a new caller with given name and telephonenumber
     *
     * @param name
     *        the name of the new caller
     * @param telephoneNumber
     *        the telephonenumber of the new caller
     */
    public Caller (String name, String telephoneNumber) {
        this.name = name;
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * Return the name of this caller
     */
    public String getName() {
        return name;
    }

    /**
     * Return the telephoneNumber of this caller
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

}
