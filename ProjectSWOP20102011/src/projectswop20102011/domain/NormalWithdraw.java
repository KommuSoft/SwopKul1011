package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidWithdrawalException;

/**
 * A class that represents the normal withdraw behavior of a unit.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class NormalWithdraw implements WithdrawBehavior {

	/**
	 * Withdraw a unit.
	 * @param unit
	 *		The unit that must be withdrawn.
	 * @effect The given unit is withdrawn from his emergency.
	 *		|unit.getEmergency().withdrawUnits(unit)
	 * @effect The emergency of the given unit is set to null.
	 *		|unit.setEmergency(null)
	 * @throws InvalidEmergencyException
	 *		If the given units isn't assigned to an emergency.
	 * @throws InvalidWithdrawalException
	 *		If the given was already at the site of the emergency.
	 */
	@Override
	public void withdraw(Unit unit) throws InvalidEmergencyException, InvalidWithdrawalException {
		if (unit.isAssigned()) {
			if (!unit.wasAlreadyAtSite()) {
				unit.getEmergency().withdrawUnit(unit);
				unit.setEmergency(null);
			} else {
				throw new InvalidWithdrawalException("The unit was already at site of the emergency, so it can't be withdrawn.");
			}
		} else {
			throw new InvalidEmergencyException("The unit is not assigned to an emergency, so it can't be withdrawn.");
		}
	}
}
