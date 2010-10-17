package projectswop20102011;

/**
 * A class that represents an operator.
 *
 * @invar Each operator must have a valid name
 *        | hasValidName()
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class Operator {

    /**
     * Variable registering the name of this operator
     */
    private final String name;

    /**
     * Initialize a new operator with given name if the name is valid.
     *
     * @param name
     *        the name of the new operator
     *
     * @throws InvalidNameException
     *      If the given name is an invalid name. For the validation rules see {@link #isValidName(String)}.
     */
    public Operator(String name) throws InvalidNameException{
		if(isValidName(name)){
			this.name = name;
		} else {
			throw new InvalidNameException();
		}
    }

    /**
     * Return the name of this operator
     * @return The name of this operator.
     */
    public String getName(){
        return name;
    }

	/**
	 * Returns true if the given name is valid. A valid name may only contain
	 *		letters or spaces.
	 * @param name
	 *		The name that must be checked.
	 * @return
	 *		True if the name only contains letters or spaces; false otherwise.
	 */
	public static boolean isValidName(String name){
		for(int i=0; i<name.length(); ++i){
			if(!Character.isLetter(name.charAt(i)) && name.charAt(i) != ' '){
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the current name is a valid name. For the validation rules see {@link #isValidName(String)}.
	 * @return
	 *		True if the current name is a valid name; false otherwise.
	 */
	public boolean hasValidName(){
		return isValidName(getName());
	}
}
