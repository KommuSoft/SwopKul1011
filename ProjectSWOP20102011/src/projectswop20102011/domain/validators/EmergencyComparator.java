package projectswop20102011.domain.validators;

import java.util.Comparator;
import projectswop20102011.domain.Emergency;

public class EmergencyComparator implements Comparator<Emergency> {

	@Override
	public int compare(Emergency o1, Emergency o2) {
		return o1.getSeverity().compareTo(o2.getSeverity());
	}
	
}
