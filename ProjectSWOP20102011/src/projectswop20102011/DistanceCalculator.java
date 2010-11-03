package projectswop20102011;

public class DistanceCalculator{

	private final GPSCoordinate gpsCoordinate;

	public DistanceCalculator(GPSCoordinate gpsCoordinate){
		this.gpsCoordinate = gpsCoordinate;
	}

	public GPSCoordinate getGPSCoordinate(){
		return gpsCoordinate;
	}

	public double getDistanceTo(GPSCoordinate gps){
		double distance;

		distance = Math.sqrt(Math.pow(gpsCoordinate.getX()-gps.getX(), 2)+Math.pow(gpsCoordinate.getY()-gps.getY(), 2));

		return distance;
	}

}