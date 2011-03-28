package projectswop20102011.domain;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public abstract class UnitsNeeded {


	/**
	 * Generates a proposal for unit allocation based on the policy of the emergency.
	 * @param availableUnits
	 *		A list of available units.
	 * @return A list of units proposed by the policy of this Emergency.
	 */
	public abstract Set<Unit> getPolicyProposal(List<? extends Unit> availableUnits);

	public abstract boolean canBeResolved(Collection<? extends Unit> availableUnits);

}
