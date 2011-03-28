package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import projectswop20102011.domain.validators.DispatchUnitsConstraint;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;

public class Disaster extends Sendable {

	private final List<Emergency> emergencies;
	private DerivedUnitsNeeded unitsNeeded;

	public Disaster(List<Emergency> emergencies, String description) {
		this.emergencies = emergencies;
		setDescription(description);
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
		long x = 0;
		long y = 0;

		for (Emergency e : getEmergencies()) {
			x += e.getLocation().getX();
			y += e.getLocation().getY();
		}

		x /= getEmergencies().size(); //TODO heeft afronding hier fouten?
		y /= getEmergencies().size(); //TODO heeft afronding hier fouten?

		return new GPSCoordinate(x, y);
	}

	/**
	 * Returns the severity of this emergency.
	 * @return The severity of this emergency.
	 */
	@Override
	public EmergencySeverity getSeverity() {
		EmergencySeverity max = EmergencySeverity.BENIGN;

		for (Emergency e : getEmergencies()) {
			if (e.getSeverity().ordinal() > max.ordinal()) {
				max = e.getSeverity();
			}
		}

		return max;
	}

	/**
	 * Returns the status of this emergency.
	 * @return The status of this emergency.
	 */
	@Override
	public EmergencyStatus getStatus() {
		boolean completed = true;
		for (int i = 0; i < getEmergencies().size() - 1; ++i) {
			if (!(getEmergencies().get(i).getStatus().equals(getEmergencies().get(i + 1).getStatus()) && getEmergencies().get(i).getStatus().equals(EmergencyStatus.COMPLETED))) {
				completed = false;
			}
		}
		if (completed) {
			return EmergencyStatus.COMPLETED;
		} else {
			return EmergencyStatus.RESPONSE_IN_PROGRESS;
		}
	}

	/**
	 * Returns a ConcreteUnitsNeeded structure that contains the units needed for this emergency.
	 * @return A ConcreteUnitsNeeded structure that contains the units needed for this emergency.
	 * @note Handling dispatching and updating the status of the emergency is also done by this object.
	 * @note The visibility of this method is package. No classes outside the domain have access to the ConcreteUnitsNeeded object.
	 */
	private synchronized List<ConcreteUnitsNeeded> getUnitsNeeded() {
		//TODO implementatie
		throw new UnsupportedOperationException("Not supported yet.");
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
	public void assignUnits(Set<Unit> units) throws InvalidEmergencyStatusException, InvalidEmergencyException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public ArrayList<Unit> getWorkingUnits() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean canAssignUnits(Set<Unit> units) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Set<Unit> getPolicyProposal(List<? extends Unit> availableUnits) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DispatchPolicy getDispatchPolicy() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DispatchUnitsConstraint getDispatchConstraint() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean canBeResolved(Collection<Unit> availableUnits) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	void finishUnit(Unit unitToFinish) throws InvalidEmergencyStatusException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	void withdrawUnit(Unit unitToWithdraw) throws InvalidEmergencyStatusException {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
