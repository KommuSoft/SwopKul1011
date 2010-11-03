package projectswop20102011;

/**
 * A class that represents a DistanceCalculator.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DistanceCalculator{

	/**
	 * A final variable registering the gpsCoordinate of this DistanceCalculator.
	 */
	private final GPSCoordinate gpsCoordinate;

	/**
	 * Creates a new DistanceCalculator with given parameters.
	 *
	 * @param gpsCoordinate
	 *		The new gpsCoordinate of this DistanceCalculator.
     * @post The gpsCoordinate of this DistanceCalculator is set according to the given gpsCoordinate.
	 *		|new.getGPSCoordinate() == gpsCoordinate
	 */
	public DistanceCalculator(GPSCoordinate gpsCoordinate){
		this.gpsCoordinate = gpsCoordinate;
	}

	/**
	 * Returns the gpsCoordinate of this DistanceCalculator.
	 * @return The gpsCoordinate of this DistanceCalculator.
	 */
	public GPSCoordinate getGPSCoordinate(){
		return gpsCoordinate;
	}

	/**
	 * Calculates the distance to a given gpsCoordinate.
	 *
	 * @param gpsCoordinate
	 *		The gpsCoordinate to calculate te distance to.
	 * @return
	 *		The distance to the given gpsCoordinate.
	 */
	public double getDistanceTo(GPSCoordinate gpsCoordinate){
		double distance;
		distance = Math.sqrt(Math.pow(getGPSCoordinate().getX()-gpsCoordinate.getX(), 2)+Math.pow(getGPSCoordinate().getY()-gpsCoordinate.getY(), 2));
		return distance;
	}

}