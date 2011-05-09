package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.ILocation;

public class LocationAdapter implements ILocation{

	LocationAdapter(int x, int y){

	}

	@Override
	public int getX() {
		throw new UnsupportedOperationException("Not supported yet.LA1");
	}

	@Override
	public int getY() {
		throw new UnsupportedOperationException("Not supported yet.LA2");
	}

	@Override
	public double getDistanceTo(ILocation location) {
		throw new UnsupportedOperationException("Not supported yet.LA3");
	}

}
