package projectswop20102011;

import java.util.regex.Pattern;

/**
 * A class representing a human being with a specific name.
 *
 * @invar Each caller must have a valid name.
 *        | hasValidName().
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class Human {

	private static Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z ]+$");
	/**
	 * Variable registering the name of this caller
	 */
	private final String name;

	/**
	 * Creates a new human being with a specific name.
	 * @param name
	 *		The name of the human being.
	 * @throws InvalidNameException
	 *		If the given name is invalid. For the validation rules see {@link #isValidName(String)}.
	 */
	protected Human(String name) throws InvalidNameException {
		if (isValidName(name)) {
			this.name = name;
		} else {
			throw new InvalidNameException();
		}
	}

	/**
	 * Returns true if the given name is valid. A valid name may only contain
	 *		letters or spaces.
	 * @param name
	 *		The name that must be checked.
	 * @return
	 *		True if the name only contains letters or spaces; false otherwise.
	 */
	public static boolean isValidName(String name) {
		return (name != null && NAME_PATTERN.matcher(name).matches());
	}

	/**
	 * Return the name of this caller
	 * @return The name of this caller.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Checks if the current name is a valid name. For the validation rules see {@link #isValidName(String)}.
	 * @return
	 *		True if the current name is a valid name; false otherwise.
	 */
	public boolean hasValidName() {
		return isValidName(getName());
	}
}
