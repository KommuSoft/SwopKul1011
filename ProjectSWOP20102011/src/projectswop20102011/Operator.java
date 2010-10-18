package projectswop20102011;

/**
 * A class that represents an operator.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class Operator extends Human{

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
		super(name);
    }
}
