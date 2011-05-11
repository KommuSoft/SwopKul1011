package be.kuleuven.cs.swop.api;

/**
 * Represents the different severities for an emergency. The severities
 * 	are described from least severe to most severe.
 * 
 * @author philippe
 *
 */
public enum Severity {

	/**
	 * Represents any of the severities listed below.
	 */
	ANY,
	
	/**
	 * Represents a severity of level "benign". This is the lowest type.
	 */
	BENIGN,
	
	/**
	 * Represents a severity of level "normal"
	 */
	
	NORMAL,
	
	/**
	 * Represents a severity of level "serious"
	 */
	
	SERIOUS,
	
	/**
	 * Represents a severity of level "urgent". This is the highest type.
	 */	
	URGENT
	
}
