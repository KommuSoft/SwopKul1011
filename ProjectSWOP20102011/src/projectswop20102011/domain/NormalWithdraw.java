package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidWithdrawalException;

/**
 * A class that represents the normal withdraw behavior of a unit.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class NormalWithdraw implements WithdrawBehavior {

	@Override
	public void withdraw(Unit u) throws InvalidEmergencyException, InvalidWithdrawalException {
		if (u.isAssigned()) {
			if (!u.wasAlreadyAtSite()) {
				u.getEmergency().getUnitsNeeded().unitFinishedJob(u);
				u.setEmergency(null);
			} else {
				throw new InvalidWithdrawalException("The unit was already at site of the emergency, so it can't be withdrawn.");
			}
		} else {
			throw new InvalidEmergencyException("The unit is not assigned to an emergency, so it can't be withdrawn.");
		}
	}
}
