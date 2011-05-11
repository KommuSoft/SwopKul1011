package projectswop20102011.domain.validators;

import java.util.Comparator;
import projectswop20102011.domain.Disaster;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidDisasterException;

public class UnitToDisasterDistanceComparator implements Comparator<Unit>  {

	private final Disaster disaster;

	public UnitToDisasterDistanceComparator(Disaster disaster) throws InvalidDisasterException{
		if (!isValidDisaster(disaster)) {
			throw new InvalidDisasterException(String.format("\"%s\" is an invalid disaster for an UnitToDisasterDistanceComparator.", disaster));
		} else {
			this.disaster = disaster;
		}
	}

	private Disaster getDisaster() {
		return disaster;
	}


	@Override
	public int compare(Unit unit1, Unit unit2) {
		Double distance1 = unit1.getCurrentLocation().getDistanceTo(getDisaster().getLocation());
		Double distance2 = unit2.getCurrentLocation().getDistanceTo(getDisaster().getLocation());
		return distance1.compareTo(distance2);
	}

	public static boolean isValidDisaster(Disaster disaster) {
		return (disaster != null);
	}

}
