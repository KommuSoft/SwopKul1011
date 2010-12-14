package projectswop20102011.externalsystem.api;

import be.kuleuven.cs.swop.api.ILocation;
import projectswop20102011.domain.GPSCoordinate;

public class Location implements ILocation{
	private final GPSCoordinate gpsCoordinate;

	public Location(int x, int y){
		gpsCoordinate = new GPSCoordinate(x, y);
	}

	private GPSCoordinate getGpsCoordinate(){
		return gpsCoordinate;
	}

	@Override
	public int getX() {
		return (int) getGpsCoordinate().getX();
	}

	@Override
	public int getY() {
		return (int) getGpsCoordinate().getX();
	}

}
