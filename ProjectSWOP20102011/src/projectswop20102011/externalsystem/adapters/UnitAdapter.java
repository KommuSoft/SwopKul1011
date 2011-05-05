package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.ILocation;
import be.kuleuven.cs.swop.api.IUnit;
import be.kuleuven.cs.swop.api.UnitState;
import be.kuleuven.cs.swop.events.Location;
import projectswop20102011.domain.Unit;

public class UnitAdapter implements IUnit{
	final private Unit unit;


	public UnitAdapter(Unit unit){
		this.unit = unit;
	}

	protected Unit getUnit(){
		return unit;
	}

	@Override
	public ILocation getLocation() {
		return new LocationAdapter((int)getUnit().getCurrentLocation().getX(), (int)getUnit().getCurrentLocation().getY());
	}

	@Override
	public ILocation getHomeLocation() {
		return new LocationAdapter((int)getUnit().getHomeLocation().getX(), (int)getUnit().getHomeLocation().getY());
	}

	@Override
	public String getName() {
		return getUnit().getName();
	}

	@Override
	public UnitState getState() {
		throw new UnsupportedOperationException("Not supported yet.");
		//return UnitState.valueOf(getUnit().getStatus());
	}

}
