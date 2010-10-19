package projectswop20102011;

import java.util.ArrayList;

/*
 * A class that represents a list of callers.
 */
public class OperatorList{
	/**
	 * Variable registering the list of operators.
	 */
	private ArrayList<Operator> operators;

	/**
	 * Makes an empty list of operators.
	 */
	public OperatorList(){
		setOperators(new ArrayList<Operator>());
	}

	/**
	 * Returns the operators in this list.
	 * @return The operators in this list.
	 */
	public ArrayList<Operator> getOperators() {
		return operators;
	}

	/**
	 * Sets the operators of this list.
	 * @param operators
	 *		The operators of this list.
	 */
	private void setOperators(ArrayList<Operator> operators) {
		this.operators = operators;
	}

	/**
	 * Adds a new operator to the current list. If the operator is already in this list then no changes occur.
	 * @param o
	 *		The new operator.
	 */
	public void addOperator(Operator o){
		if(!getOperators().contains(o)){
			ArrayList<Operator> t = getOperators();
			t.add(o);
			setOperators(t);
		}
	}

}