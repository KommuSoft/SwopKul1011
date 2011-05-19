package projectswop20102011.controllers;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import projectswop20102011.domain.validators.AvailableUnitsMapItemValidator;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.validators.MapItemValidator;
import projectswop20102011.domain.validators.UnitToEmergencyDistanceComparator;
import projectswop20102011.World;
import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.EmergencyEventHandler;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.Policecar;
import projectswop20102011.domain.validators.TypeUnitValidator;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidSendableStatusException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller to handle unit dispatching.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DispatchUnitsToEmergencyController extends Controller {
	
	private EmergencyEventHandler eventHandler;

	/**
	 * Creates a new instance of a DispatchUnitsController with a given word to operate on.
	 * @param world The world where the controller will operate on.
	 * @throws InvalidWorldException If the given world is invalid.
	 */
	public DispatchUnitsToEmergencyController(World world, EmergencyEventHandler eventHandler) throws InvalidWorldException {
		super(world);
		this.eventHandler = eventHandler;
	}

	/**
	 * Generates a proposal for unit allocation for the given emergency.
	 * @param emergency The emergency to generate a proposal for.
	 * @return A set of Units that represent the proposal for the emergency.
	 * @throws InvalidEmergencyException If the given emergency is invalid.
	 */
	public Set<Unit> getUnitsByPolicy(Emergency emergency) {
		MapItemValidator<Unit> criterium = new AvailableUnitsMapItemValidator();
		HashSet<Unit> mapItems = getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getMapItems();
		return emergency.getPolicyProposal(mapItems);
	}

	/**
	 * Returns a list of available units sorted on the policy used by the givem emergency.
	 * @param emergency The emergency to sort the units.
	 * @return A list of available units sorted on the policy used by the given emergency.
	 * @throws InvalidEmergencyException
	 *          If the given emergency is invalid.
	 * @throws InvalidClassException  
	 */
	public List<Unit> getAvailableUnitsSorted(Emergency emergency) throws InvalidEmergencyException, InvalidClassException {
		//TODO: dit kan waarschijnlijk iets mooier?
		MapItemValidator criterium = new AvailableUnitsMapItemValidator();
		Set<Unit> units = getUnitsByPolicy(emergency);
		TypeUnitValidator tuv1 = new TypeUnitValidator(Ambulance.class);
		TypeUnitValidator tuv2 = new TypeUnitValidator(Policecar.class);
		TypeUnitValidator tuv3 = new TypeUnitValidator(Firetruck.class);
		boolean ambulance = false;
		boolean firetruck = false;
		boolean policecar = false;
		for (Unit u : units) {
			ambulance = ambulance || tuv1.isValid(u);
			firetruck = firetruck || tuv3.isValid(u);
			policecar = policecar || tuv2.isValid(u);
		}
		List<Unit> allUnits = this.getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getSortedCopy(new UnitToEmergencyDistanceComparator(emergency));
		List<Unit> result = new ArrayList<Unit>(0);
		for (Unit u : allUnits) {
			if (ambulance && u instanceof Ambulance) {
				result.add(u);
			}
			if (policecar && u instanceof Policecar) {
				result.add(u);
			}
			if (firetruck && u instanceof Firetruck) {
				result.add(u);
			}
		}
		return result;
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
	 * @throws InvalidEmergencyStatusException
	 *          If the emergency is in a state where units can't be dispatched to.
	 * @throws InvalidEmergencyException 
	 */
	public void dispatchToEmergency(Emergency emergency, Set<Unit> units) throws InvalidSendableStatusException, InvalidEmergencyException {
		if (emergency.isPartOfADisaster()) {
			throw new InvalidEmergencyException("The emergency is part of a disaster.");
		}
		emergency.assignUnits(units, eventHandler);
	}
}
