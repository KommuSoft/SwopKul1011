package projectswop20102011;

/**
 * A class of callers.
 *
 * @invar Each caller must have a valid name
 *        | hasValidName()
 * @invar Each caller must have a valid phone number
 *        | hasValidPhoneNumber()
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class Caller {

    /**
     * Variable registering the name of this caller
     */
    private final String name;

    /**
     * Variable registering the phone number of this caller
     */
    private final String phoneNumber;

    /**
     * Initialize a new caller with given name and phone number if both the
     * name and phone number are valid.
     *
     * @param name
     *        the name of the new caller
     * @param phoneNumber
     *        the phone number of the new caller
     * @throws InvalidNameException
     *  If the given name is invalid.
     * @throws InvalidPhoneNumberException
     *  if the given phone number is invalid.
     */
    public Caller (String name, String phoneNumber) throws InvalidNameException, InvalidPhoneNumberException{
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Return the name of this caller
     * @return The name of this caller.
     */
    public String getName() {
        return name;
    }

    /**
     * Return the phone number of this caller
     * @return The phone number of this caller.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

}
