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
     * @post The x-coordinate of this GPSCoordinate is set according to the given x-coordinate.
	 *		|new.getX() == x
     * @post The y-coordinate of this GPSCoordinate is set according to the given y-coordinate.
	 *		|new.getY() == y
     */
    public GPSCoordinate(long x, long y){
        this.x = x;
		this.y = y;
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
     * Returns a textual representation of this GPSCoordinate.
     * @return a textual representation of this GPSCoordinate.
     */
    @Override
    public String toString () {
        return String.format("(%s,%s)",this.getX(),this.getY());
    }

}