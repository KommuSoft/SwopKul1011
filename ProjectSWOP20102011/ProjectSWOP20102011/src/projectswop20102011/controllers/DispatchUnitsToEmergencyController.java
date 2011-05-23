package projectswop20102011.controllers;

import be.kuleuven.cs.swop.external.IExternalSystem;
import java.util.Set;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.Unit;
import projectswop20102011.World;
import projectswop20102011.eventhandlers.ExternalSystemEventHandler;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidSendableStatusException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller for the use case where units are dispatched to an emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DispatchUnitsToEmergencyController extends DispatchController {
	
	/**
	 * Creates a new instance of a DispatchUnitsController with a given word to operate on.
	 * @param world The world where the controller will operate on.
	 * @param externalSystem 
	 * @param eventHandler 
	 * @throws InvalidWorldException If the given world is invalid.
	 */
	public DispatchUnitsToEmergencyController(World world) throws InvalidWorldException {
		super(world);
	}

	/**
	 * Returns a textual representation of the unit constraints of the given emergency.
	 * @param emergency The given emergency.
	 * @return A textual representation of the unit constraints of the given emergency.
	 */
	public String getRequiredUnits(Emergency emergency) {
		return emergency.getDispatchConstraint().toString();
	}

	/**
	 * Dispatches the given set of units to the given emergency.
	 * @param emergency The emergency where the units will be assigned to.
	 * @param units The set of units to assign to the given emergency.
	 * @throws InvalidSendableStatusException 
	 * @throws InvalidEmergencyException
	 */
	public void dispatchToEmergency(Emergency emergency, Set<Unit> units) throws InvalidSendableStatusException, InvalidEmergencyException {
		if (emergency.isPartOfADisaster()) {
			throw new InvalidEmergencyException("The emergency is part of a disaster.");
		}
		emergency.assignUnits(units);
	}
}
