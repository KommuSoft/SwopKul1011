package projectswop20102011;

/**
 * A class that represents a GPSCoordinate.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class GPSCoordinate {

    /**
     * A variable that registers the x coordinate of the GPS coordinate.
     */
    private final long x;
    /**
     * A variable that registers the y coordinate of the GPS coordinate.
     */
    private final long y;

    /**
     * Creates a new GPS coordinate.
     * @param x
     *		The x coordinate of this GPS coordinate.
     * @param y
	 *		The y coordinate of this GPS coordinate.
	 * @throws InvalidCoordinateException
	 *		If the given coordinates are invalid. For the validation rules see
	 *		{@link #isValidXCoordinate(long)} and {@link #isValidYCoordinate(long)}.
     */
    public GPSCoordinate(long x, long y) throws InvalidCoordinateException {
        if(isValidXCoordinate(x)){
			this.x = x;
		} else {
			throw new InvalidCoordinateException();
		}
		if(isValidYCoordinate(y)){
			this.y = y;
		} else {
			throw new InvalidCoordinateException();
		}
    }

    /**
     * Returns the x coordinate of this GPS coordinate.
     * @return The x coordinate of this GPS coordinate.
     */
    public long getX() {
        return x;
    }

    /**
     * Returns the y coordinate of this GPS coordinate.
     * @return The y coordinate of this GPS coordinate.
     */
    public long getY() {
        return y;
    }

    /**
     * Returns true is the x coordinate is a valid x coordinate.
     * @param x
     *		The x coordinate that must be checked.
     * @return
     *		True if the x coordinate is a valid x coordinate; false otherwise.
     */
    public static boolean isValidXCoordinate(long x) {
        //Moet nog geimplementeerd worden
        return true;
    }

    /**
     * Returns true is the y coordinate is a valid y coordinate.
     * @param y
     *		The y coordinate that must be checked.
     * @return
     *		True if the y coordinate is a valid y coordinate; false otherwise.
     */
    public static boolean isValidYCoordinate(long y) {
        //Moet nog geïmplementeerd worden
        return true;
    }
}