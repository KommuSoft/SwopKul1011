package projectswop20102011;

/**
 * Implementing this interface means that the object becomes time sensitive.
 * I.e. it state depends on the current time.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public interface TimeSensitive {
	public void advanceTime();
}
