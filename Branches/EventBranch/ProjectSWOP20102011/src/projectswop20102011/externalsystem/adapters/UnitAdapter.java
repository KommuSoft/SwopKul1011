package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.ILocation;
import be.kuleuven.cs.swop.api.IUnit;
import be.kuleuven.cs.swop.api.UnitState;
import projectswop20102011.domain.Unit;

/**
 * An adapter that offers operations to inspect a specific unit.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class UnitAdapter implements IUnit {

	/**
	 * The Unit of this UnitAdapter.
	 */
	final private Unit unit;

	/**
	 * Creates a UnitAdapter with the given Unit.
	 * @param unit
	 *		The Unit that is used to create the UnitAdapter.
	 */
	public UnitAdapter(Unit unit) {
		this.unit = unit;
	}

	/**
	 * Returns the Unit of this UnitAdapter.
	 * @return The Unit of this UnitAdapter
	 */
	public Unit getUnit() {
		return unit;
	}

	/**
	 * Retrieves the current location of the unit.
	 * @return The current location of the unit.
	 */
	@Override
	public ILocation getLocation() {
		return new LocationAdapter(unit.getCurrentLocation());
	}

	/**
	 * Retrieves the home location of the unit.
	 * @return The home location of the unit.
	 */
	@Override
	public ILocation getHomeLocation() {
		return new LocationAdapter(unit.getHomeLocation());
	}

	/**
	 * Retrieves the name of the unit.
	 * @return The name of the unit.
	 */
	@Override
	public String getName() {
		return getUnit().getName();
	}

	/**
	 * Retrieves the current state of the unit within the system.
	 * @return The state of the unit.
	 */
	@Override
	public UnitState getState() {
		return UnitState.valueOf(getUnit().getUnitStatus().toString());
	}
}
