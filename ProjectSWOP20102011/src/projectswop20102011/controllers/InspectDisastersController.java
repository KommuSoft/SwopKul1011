package projectswop20102011.controllers;

import java.util.Map.Entry;
import java.util.Set;
import projectswop20102011.World;
import projectswop20102011.domain.Disaster;
import projectswop20102011.domain.SendableStatus;
import projectswop20102011.domain.validators.DisasterEvaluationCriterium;
import projectswop20102011.domain.validators.StatusEqualityDisasterEvaluationCriterium;
import projectswop20102011.exceptions.InvalidAddedDisasterException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller for the inspect disasters use case.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InspectDisastersController extends Controller {

	/**
	 * Creates a new instance of the InspectDisastersController with a given world to be modified.
	 * @param world The world to be modified.
	 * @effect super(world)
	 * @throws InvalidWorldException If the given world is ineffective.
	 */
	public InspectDisastersController(World world) throws InvalidWorldException {
		super(world);
	}

	/**
	 * Inspects all the disasters in the world on a certain status, and returns a list with disasters that have that status.
	 * @param status The status of to check the disasters on.
	 * @return A list with disasters with the given status.
	 * @throws InvalidAddedDisasterException  
	 */
	public Disaster[] inspectDisastersOnStatus(SendableStatus status) throws InvalidAddedDisasterException {
		DisasterEvaluationCriterium criterium = new StatusEqualityDisasterEvaluationCriterium(status);
		return this.getWorld().getDisasterList().getDisastersByCriterium(criterium).toArray();
	}

	/**
	 * Generates a set of entries with parts of information of the given disaster.
	 * @param disaster The disaster to generate information from.
	 * @return A set of entries containing the key and value of a piece of information of the disaster.
	 */
	public Set<Entry<String, String>> getDisasterShortInformation(Disaster disaster) {
		return disaster.getShortInformation().entrySet();
	}
}
