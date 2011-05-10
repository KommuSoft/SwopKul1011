package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.ILocation;
import projectswop20102011.domain.GPSCoordinate;

public class LocationAdapter implements ILocation{

	private final GPSCoordinate location;

	LocationAdapter(GPSCoordinate location){
	this.location = location;
	}

	public GPSCoordinate getGPSCoordinate(){
		return location;
	}

	@Override
	public int getX() {
		return (int) getGPSCoordinate().getX();
	}

	@Override
	public int getY() {
		return (int) getGPSCoordinate().getY();
	}

	@Override
	public double getDistanceTo(ILocation location) {
		throw new UnsupportedOperationException("Not supported yet.LA3");
	}

	@Override
	public String toString() {
		return "(" + getX() + "," + getY() +")";
	}

}
