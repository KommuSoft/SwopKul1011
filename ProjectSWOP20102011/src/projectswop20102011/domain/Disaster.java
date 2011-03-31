package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;

public class Disaster extends Sendable {

	private final List<Emergency> emergencies;
	private DerivedUnitsNeeded unitsNeeded;

	public Disaster(List<Emergency> emergencies, String description) throws InvalidEmergencyException {
		if (!areValidEmergencies(emergencies)) {
			throw new InvalidEmergencyException("The number of emergencies must be higher than one.");
		}
		this.emergencies = emergencies;
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
		//TODO: moet dit niet berekend worden in GPSCoordinate?
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
		EmergencyStatus status = this.getEmergencies().get(0).getStatus();//TODO: wat indien Disaster geen Emergencies bevat? Antwoord: Een disaster bevat per definitie altijd minstens 1 emergency denk ik. (Zie pagina 9, alternate flow van use case create dissaster).
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
	public void assignUnits(Set<Unit> units) throws InvalidEmergencyStatusException, InvalidEmergencyException {
		ArrayList<Unit> options = new ArrayList<Unit>(units.size());
		Iterator<Unit> it = units.iterator();
		while (it.hasNext()) {
			options.add(it.next());
		}

		for (Emergency e : getEmergencies()) {
			ConcreteUnitsNeeded CUN = e.getUnitsNeeded();
			Set<Unit> proposal = CUN.generateProposal(options);
			getStatus().assignUnits(e.getUnitsNeeded(), proposal);
			options.removeAll(proposal);
		}
	}

	@Override
	public ArrayList<Unit> getWorkingUnits() {
		ArrayList<Unit> workingUnits = new ArrayList<Unit>();
		for (Emergency e : getEmergencies()) {
			workingUnits.addAll(e.getWorkingUnits());
		}
		return workingUnits;
	}

	@Override
	public boolean canAssignUnits(Set<Unit> units) {
		for (Unit u : units) {
			boolean used = false;
			HashSet<Unit> unit = new HashSet<Unit>();
			unit.add(u);
			for (Emergency e : getEmergencies()) {
				if(e.canAssignUnits(unit)){
					used = true;
				}
			}
			if(!used){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean canBeResolved(Collection<Unit> availableUnits) {
		ArrayList<Unit> options = new ArrayList<Unit>(availableUnits.size());
		Iterator<Unit> it = availableUnits.iterator();
		while (it.hasNext()) {
			options.add(it.next());
		}

		for (Emergency e : getEmergencies()) {
			ConcreteUnitsNeeded CUN = e.getUnitsNeeded();
			Set<Unit> proposal = CUN.generateProposal(options);
			if (!getStatus().canBeResolved(e.getUnitsNeeded(), proposal)) {
				return false;
			} else {
				options.removeAll(proposal);
			}
		}
		return true;
	}

	@Override
	void finishUnit(Unit unitToFinish) throws InvalidEmergencyStatusException {
		for (Emergency e : getEmergencies()) {
			getStatus().finishUnit(e.getUnitsNeeded(), unitToFinish);
		}
	}

	@Override
	void withdrawUnit(Unit unitToWithdraw) throws InvalidEmergencyStatusException {
		for (Emergency e : getEmergencies()) {
			getStatus().withdrawUnit(e.calculateUnitsNeeded(), unitToWithdraw);
		}
	}

	@Override
	public Set<Unit> getPolicyProposal(List<? extends Unit> availableUnits) {
		Set<Unit> units = null;
		if (getEmergencies().size() > 0) {
			units = getStatus().getPolicyProposal(getEmergencies().get(0).calculateUnitsNeeded(), availableUnits);
			for (int i = 1; i < getEmergencies().size(); ++i) {
				units.addAll(getStatus().getPolicyProposal(getEmergencies().get(0).calculateUnitsNeeded(), availableUnits));
			}
		}

		return units;
	}
}
