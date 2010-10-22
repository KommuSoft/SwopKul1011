package projectswop20102011;

import java.util.ArrayList;

/*
 * A class that represents a list of callers.
 */
public class OperatorList {

    /**
     * Variable registering the list of operators.
     */
    private static ArrayList<Operator> operators = new ArrayList<Operator>();

    //private, no instances of OperatorList can be generated
    private OperatorList() {}

    /**
     * Adds a new operator to the OperatorList. If the operator is already in the list then no changes occur.
     * @param o
     *		The new operator.
     */
    static void addOperator(Operator o) {
        if (!operators.contains(o)) {
            operators.add(o);
        }
    }
    /**
     * Checks if the OperatorList contains already an operator with a given name.
     * @param name the name to check the existance of.
     * @return true if OperatorList contains already an operator with that name, otherwise false.
     */
    public static boolean containsName (String name) {
        for(Operator o : operators) {
            if(o.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

}
