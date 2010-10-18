package projectswop20102011;

/**
 * A class that represents a caller.
 *
 * @invar Each caller must have a valid phone number.
 *        | hasValidPhoneNumber()
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class Caller extends Human {

    /**
     * Variable registering the phone number of this caller
     */
    private final String phoneNumber;

    /**
     * Initialize a new caller with given name and phone number.
     *
     * @param name
     *		the name of the new caller
     * @param phoneNumber
     *		the phone number of the new caller
     * @throws InvalidNameException
     *		If the given name is invalid. For the validation rules see {@link #isValidName(String)}.
     * @throws InvalidPhoneNumberException
     *		If the given phone number is invalid. For the validation rules see {@link #isValidPhoneNumber(String)}.
     */
    public Caller (String name, String phoneNumber) throws InvalidNameException, InvalidPhoneNumberException{
		super(name);
		if(isValidPhoneNumber(phoneNumber)){
			this.phoneNumber = phoneNumber;
		} else {
			throw new InvalidPhoneNumberException();
		}
    }

    /**
     * Return the phone number of this caller
     * @return The phone number of this caller.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

	/**
	 * Returns true if the given phone number is valid. A valid phone number is
	 *		a phone number that only contains digits.
	 * @param phoneNumber
	 *		The phone number that must be checked.
	 * @return
	 *		True if the phone number only contains digits; false otherwise.
	 */
	public static boolean isValidPhoneNumber(String phoneNumber){
		for(int i=0; i<phoneNumber.length(); ++i){
			if(!Character.isDigit(phoneNumber.charAt(i))){
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the current phone number is a valid phone number. For the validation rules see {@link #isValidPhoneNumber(String)}.
	 * @return
	 *		True if the current phone number is a valid phone number; false otherwise.
	 */
	public boolean hasValidPhoneNumber(){
		return isValidPhoneNumber(getPhoneNumber());
	}
}
