package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.ILocation;
import be.kuleuven.cs.swop.api.IUnit;
import be.kuleuven.cs.swop.api.UnitState;
import projectswop20102011.domain.Unit;

public class UnitAdapter implements IUnit{
	final private Unit unit;


	public UnitAdapter(Unit unit){
		this.unit = unit;
	}

	public Unit getUnit(){
		return unit;
	}

	@Override
	public ILocation getLocation() {
		return new LocationAdapter(unit.getCurrentLocation());
	}

	@Override
	public ILocation getHomeLocation() {
		return new LocationAdapter(unit.getHomeLocation());
	}

	@Override
	public String getName() {
		return getUnit().getName();
	}

	@Override
	public UnitState getState() {
		return UnitState.valueOf(getUnit().getUnitStatus().toString());
	}

}
