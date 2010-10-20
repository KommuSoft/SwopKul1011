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
    private long x;
    /**
     * A variable that registers the y coordinate of the GPS coordinate.
     */
    private long y;

    /**
     * Creates a new GPS coordinate.
     * @param x
     *		The x coordinate of this GPS coordinate.
     * @param y
     *		The y coordinate of this GPS coordinate.
     */
    public GPSCoordinate(long x, long y) {
        setX(x);
        setY(y);
    }

    /**
     * Returns the x coordinate of this GPS coordinate.
     * @return The x coordinate of this GPS coordinate.
     */
    public long getX() {
        return x;
    }

    /**
     * Sets the x coordinate of this GPS coordinate.
     * @param x
     *		The x coordinate of this GPS coordinate.
     */
    private void setX(long x) {
        this.x = x;
    }

    /**
     * Returns the y coordinate of this GPS coordinate.
     * @return The y coordinate of this GPS coordinate.
     */
    public long getY() {
        return y;
    }

    /**
     * Sets the y coordinate of this GPS coordinate.
     * @param y
     *		The y coordinate of this GPS coordinate.
     */
    private void setY(long y) {
        this.y = y;
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
        return false;
    }

    /**
     * Checks if the current x coordinate is a valid x coordinate. For the validation rules see {@link #isValidXCoordinate(long)}.
     * @return
     *		True if the current x coordinate is a valid x coordinate; false otherwise.
     */
    public boolean hasValidXCoordinate() {
        return isValidXCoordinate(getX());
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
        return false;
    }

    /**
     * Checks if the current y coordinate is a valid y coordinate. For the validation rules see {@link #isValidYCoordinate(long)}.
     * @return
     *		True if the current y coordinate is a valid y coordinate; false otherwise.
     */
    public boolean hasValidYCoordinate() {
        return isValidYCoordinate(getY());
    }
}
