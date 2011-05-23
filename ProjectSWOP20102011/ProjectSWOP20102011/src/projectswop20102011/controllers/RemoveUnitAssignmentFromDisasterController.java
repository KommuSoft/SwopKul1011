package projectswop20102011.controllers;

import be.kuleuven.cs.swop.external.IExternalSystem;
import java.util.ArrayList;
import projectswop20102011.World;
import projectswop20102011.domain.Disaster;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidSendableStatusException;
import projectswop20102011.exceptions.InvalidMapItemException;
import projectswop20102011.exceptions.InvalidWithdrawalException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller used for the use case where a unit is withdrawn from a disaster.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class RemoveUnitAssignmentFromDisasterController extends Controller {
	
	public RemoveUnitAssignmentFromDisasterController(World world) throws InvalidWorldException {
		super(world);
	}

	/**
	 * Generates a list containing all the Units that are working on the given Emergency.
	 * @param selectedEmergency The emergency where we generate the working units list from.
	 * @return A list containing all the Units that working on the given Emergency.
	 */
	public ArrayList<Unit> getWorkingUnits(Disaster selectedDisaster) {
		return selectedDisaster.getWorkingUnits();
	}

	/**
	 * Withdraws a Unit from its emergency.
	 * @param unit The unit to withdraw from it's emergency.
	 * @throws InvalidWithdrawalException If the unit cannot withdraw.
	 * @throws InvalidEmergencyStatusException If the status of the emergency does not allow units to withdraw.
	 * @throws InvalidMapItemException If the given unit is not effective.
	 */
	public void withdrawUnit(Unit unit) throws InvalidWithdrawalException, InvalidSendableStatusException, InvalidMapItemException {
		if (unit != null) {
			unit.withdraw();
		} else {
			throw new InvalidMapItemException("MapItem must be a Unit and exist in the World!");
		}
	}
}
