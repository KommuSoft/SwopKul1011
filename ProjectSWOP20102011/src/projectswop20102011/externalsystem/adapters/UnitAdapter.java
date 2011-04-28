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

	private Unit getUnit(){
		return unit;
	}

	@Override
	public ILocation getLocation() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public ILocation getHomeLocation() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String getName() {
		return getUnit().getName();
	}

	@Override
	public UnitState getState() {
		return UnitState.valueOf(getUnit().getStatus())
	}

}
