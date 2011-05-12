package projectswop20102011.domain;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidConstraintListException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.utils.GetterMapFunction;
import projectswop20102011.utils.MapFunction;

/**
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Disaster extends Sendable {

	/**
	 * The emergencies of this Disaster.
	 */
	private final List<Emergency> emergencies;

	/**
	 * The units that are needed for this Disaster.
	 */
	private final DerivedUnitsNeeded unitsNeeded;

	/**
	 * Createa disaster with the given emergencies and description.
	 * @param emergencies
	 *		The emergencies of the Disaster.
	 * @param description
	 *		The description of the Disaster.
	 * @throws InvalidEmergencyException
	 *		If the given emergencies are invalid.
	 * @throws InvalidConstraintListException
	 *		If the constraints for this disaster are invalid.
	 */
	public Disaster(List<Emergency> emergencies, String description) throws InvalidEmergencyException, InvalidConstraintListException {
		if (!areValidEmergencies(emergencies)) {
			throw new InvalidEmergencyException("The number of emergencies must be higher than one.");
		}
		this.emergencies = emergencies;
		unitsNeeded = new DerivedUnitsNeeded(this);
		setDescription(description);
	}

	/**
	 * Checks if the given emergencies are valid emergencies for a Disaster.
	 * @param emergencies
	 *		The emergencies to check if they are valid for a Disaster.
	 * @return True if the given emergencies are valid emergencies for a Disaster; false otherwise.
	 */
	private boolean areValidEmergencies(List<Emergency> emergencies) {
		return !emergencies.isEmpty();
	}

	/**
	 * Returns the emrgencies of this Disaster.
	 * @return The emergencies of this Disaster.
	 */
	public List<Emergency> getEmergencies() {
		return emergencies;
	}

	/**
	 * Returns the location of this Disaster.
	 * @return The location of this Disaster.
	 * @note The location of the Disaster is the average of its emergencies.
	 */
	@Override
	public GPSCoordinate getLocation() {
		try {
			return GPSCoordinate.calculateAverage(MapFunction.mapCollection(this.getEmergencies(), new GetterMapFunction<Emergency, GPSCoordinate>(Emergency.class, GPSCoordinate.class, Emergency.class.getMethod("getLocation"))));
		} catch (NoSuchMethodException ex) {
			//We assume this can't happen
			Logger.getLogger(Disaster.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SecurityException ex) {
			//We assume this can't happen
			Logger.getLogger(Disaster.class.getName()).log(Level.SEVERE, null, ex);
		} catch (Throwable ex) {
			//We assume this can't happen
			Logger.getLogger(Disaster.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	/**
	 * Returns the severity level of the Disaster.
	 * @return The severity level of the Disaster.
	 * @note The severity level of the Disaster is the maximum severity level of its emergencies.
	 */
	@Override
	public EmergencySeverity getSeverity() {
		EmergencySeverity max = this.getEmergencies().get(0).getSeverity();
		for (Emergency e : getEmergencies()) {
			max = max.getMaximum(e.getSeverity());
		}
		return max;
	}

	/**
	 * Returns the status of this Disaster.
	 * @return The status of this Disaster.
	 */
	@Override
	public EmergencyStatus getStatus() {
		EmergencyStatus status = this.getEmergencies().get(0).getStatus();
		for (int i = 1; i < this.getEmergencies().size(); i++) {
			status = status.combine(this.getEmergencies().get(i).getStatus());
		}
		return status;
	}

	/**
	 * Returns a hashtable that contains the information of this emergency.
	 * This hashtable contains the id, location, severity, status and the working units.
	 * @return A hashtable that contains the information of this emergency.
	 */
	@Override
	protected Hashtable<String, String> getInformation() {
		Hashtable<String, String> information = super.getInformation();

		for (Emergency e : getEmergencies()) {
			information.putAll(e.getShortInformation());
		}

		return information;
	}

	/**
	 * Returns a hashtable that contains the extended information of this emergency.
	 * This hashtable contains the id, type, location, severity, status, the working units and specific elements of the child of this emergency.
	 * @return A hashtable that contains the extended information of this emergency.
	 */
	@Override
	public Hashtable<String, String> getLongInformation() {
		Hashtable<String, String> information = super.getInformation();

		for (Emergency e : getEmergencies()) {
			information.putAll(e.getLongInformation());
		}

		return information;
	}

	/**
	 * Returns a ConcreteUnitsNeeded structure that contains the units needed for this Disaster.
	 * @return A ConcreteUnitsNeeded structure that contains the units needed for this Disaster.
	 * @note Handling dispatching and updating the status of the Disaster is also done by this object.
	 * @note The visibility of this method is package. No classes outside the domain have access to the ConcreteUnitsNeeded object.
	 */
	@Override
	DerivedUnitsNeeded getUnitsNeeded() {
		return unitsNeeded;
	}

	/**
	 * Returns a proposal generated by the policy of this constraint.
	 * @param availableUnits
	 *      A list of units that are available for the proposal.
	 * @return A list of units proposed by the policy of this constraint.
	 */
	@Override
	public Set<Unit> getPolicyProposal(Set<Unit> availableUnits) {
		Set<Unit> units = null;
		if (getEmergencies().size() > 0) {
			units = getStatus().getPolicyProposal(getEmergencies().get(0).getUnitsNeeded(), availableUnits);
			for (int i = 1; i < getEmergencies().size(); ++i) {
				units.addAll(getStatus().getPolicyProposal(getEmergencies().get(0).getUnitsNeeded(), availableUnits));
			}
		}

		return units;
	}

	/**
	 * Enable a unit to finish his job for this Disaster.
	 * @param unitToFinish
	 *      The unit that wants to finish this Disaster.
	 * @throws InvalidEmergencyStatusException
	 *      If the status of this Disaster does not allow this action.
	 * @note This method has a package visibility: Units need to finish on their own and call this method to register this to the Disaster.
	 */
	@Override
	void finishUnit(Unit unitToFinish) throws InvalidEmergencyStatusException {
		this.getStatus().finishUnit(unitToFinish.getEmergency().getUnitsNeeded(), unitToFinish);
	}
}
