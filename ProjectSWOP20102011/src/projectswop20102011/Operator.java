package projectswop20102011;

/**
 * A class of operators
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
     *      If the given name is an invalid name.
     */
    public Operator(String name) throws InvalidNameException{
        this.name = name;
    }

    /**
     * Return the name of this operator
     * @return The name of this operator.
     */
    public String getName(){
        return name;
    }

}
