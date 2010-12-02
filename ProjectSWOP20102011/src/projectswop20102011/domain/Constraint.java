package projectswop20102011.domain;

/**
 * A interface that represents a constraint.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public interface Constraint {

	/**
	 * Checks if the constraint is valid.
	 *
	 * @return True if the constraint is valid; false otherwise.
	 */
	public boolean isValid();
}



//TODO Wie controleert de policy zodat de capaciteit van de firetruck zo dicht mogelijk bij de andere zit.
