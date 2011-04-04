package projectswop20102011.domain;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidConstraintListException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.utils.GetterMapFunction;
import projectswop20102011.utils.MapFunction;

public class Disaster extends Sendable {

	private final List<Emergency> emergencies;
	private final DerivedUnitsNeeded unitsNeeded;

	public Disaster(List<Emergency> emergencies, String description) throws InvalidEmergencyException, InvalidConstraintListException {
		if (!areValidEmergencies(emergencies)) {
			throw new InvalidEmergencyException("The number of emergencies must be higher than one.");
		}
		this.emergencies = emergencies;
		unitsNeeded = new DerivedUnitsNeeded(this);
		setDescription(description);
	}

	private boolean areValidEmergencies(List<Emergency> emergencies) {
		return !emergencies.isEmpty();
	}

	public List<Emergency> getEmergencies() {
		return emergencies;
	}

	/**
	 * Returns the location of this emergency.
	 * @return The location of this emergency.
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
	 * Returns the severity level of the disaster.
	 * @return The severity level of the disaster.
	 * @note The severity level of the disaster is the maximum severity level of its emergencies.
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
	 * Returns the status of this emergency.
	 * @return The status of this emergency.
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

	@Override
	public Hashtable<String, String> getLongInformation() {
		Hashtable<String, String> information = super.getInformation();

		for (Emergency e : getEmergencies()) {
			information.putAll(e.getLongInformation());
		}

		return information;
	}

	@Override
	DerivedUnitsNeeded getUnitsNeeded() {
		return unitsNeeded;
	}

	@Override
	public Set<Unit> getPolicyProposal(List<? extends Unit> availableUnits) {
		Set<Unit> units = null;
		if (getEmergencies().size() > 0) {
			units = getStatus().getPolicyProposal(getEmergencies().get(0).getUnitsNeeded(), availableUnits);
			for (int i = 1; i < getEmergencies().size(); ++i) {
				units.addAll(getStatus().getPolicyProposal(getEmergencies().get(0).getUnitsNeeded(), availableUnits));
			}
		}

		return units;
	}
}
