package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class DerivedUnitsNeeded extends UnitsNeeded {

	private final Disaster disaster;

	DerivedUnitsNeeded(Disaster disaster) {
		this.disaster = disaster;
	}

	private Disaster getDisaster() {
		return disaster;
	}

	/**
	 * Decides whether the emergency can be handled by the given units.
	 * @param availableUnits
	 *		The units to check if they can handle the emergency.
	 * @return True if the given units can handle the emrgency; false otherwise.
	 */
	@Override
	public boolean canBeResolved(Collection<? extends Unit> availableUnits) {
		for (Emergency e : getDisaster().getEmergencies()) {
			if (!e.calculateUnitsNeeded().canBeResolved(availableUnits)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public Set<Unit> getPolicyProposal(List<? extends Unit> availableUnits) {
		Set<Unit> units = null;
		if (getDisaster().getEmergencies().size() > 0) {
			units = getDisaster().getEmergencies().get(0).calculateUnitsNeeded().getPolicyProposal(availableUnits);
			for (int i = 1; i < getDisaster().getEmergencies().size(); ++i) {
				units.addAll(getDisaster().getEmergencies().get(i).calculateUnitsNeeded().getPolicyProposal(availableUnits));
			}
		}

		return units;
	}

	@Override
	void unitFinishedJob(Unit unit) {
		for(Emergency e : getDisaster().getEmergencies()){
			e.calculateUnitsNeeded().unitFinishedJob(unit);
		}
	}

	@Override
	Set<Unit> generateProposal(List<Unit> options) {
		Set<Unit> units = null;
		if (getDisaster().getEmergencies().size() > 0) {
			units = getDisaster().getEmergencies().get(0).calculateUnitsNeeded().generateProposal(options);
			for (int i = 1; i < getDisaster().getEmergencies().size(); ++i) {
				units.addAll(getDisaster().getEmergencies().get(i).calculateUnitsNeeded().generateProposal(options));
			}
		}

		return units;
	}

	@Override
	public ArrayList<Unit> getFinishedUnits() {
		ArrayList<Unit> units = new ArrayList<Unit>();
		for (Emergency e : getDisaster().getEmergencies()) {
			units.addAll(e.calculateUnitsNeeded().getFinishedUnits());
		}

		return units;
	}

	@Override
	public ArrayList<Unit> getWorkingUnits() {
		ArrayList<Unit> units = new ArrayList<Unit>();
		for (Emergency e : getDisaster().getEmergencies()) {
			units.addAll(e.calculateUnitsNeeded().getWorkingUnits());
		}

		return units;
	}

	@Override
	public boolean canCompleteEmergency() {
		for (Emergency e : getDisaster().getEmergencies()) {
			if (!e.calculateUnitsNeeded().canCompleteEmergency()) {
				return false;
			}
		}
		return true;
	}
}
