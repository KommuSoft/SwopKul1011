package projectswop20102011.userinterface;

import projectswop20102011.controllers.Controller;
import projectswop20102011.controllers.DisasterMapper;
import projectswop20102011.controllers.DispatchUnitsToDisasterController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;

public class DispatchUnitsToDisasterUserInterface extends CommandUserInterface {

	private final DispatchUnitsToDisasterController dispatchUnitsToDisasterController;
	private final DisasterMapper disasterController;

	public DispatchUnitsToDisasterUserInterface(DispatchUnitsToDisasterController dispatchUnitsToDisasterController, DisasterMapper disasterController) throws InvalidCommandNameException, InvalidControllerException {
		super("dispatch units to disaster");

		if (dispatchUnitsToDisasterController == null || disasterController == null) {
			throw new InvalidControllerException("Controller must be effective.");
		}
		this.dispatchUnitsToDisasterController = dispatchUnitsToDisasterController;
		this.disasterController = disasterController;
	}

	@Override
	public Controller getController() {
		return this.dispatchUnitsToDisasterController;
	}

	private DisasterMapper getDisasterController(){
		return disasterController;
	}

	@Override
	public void handleUserInterface() {
		throw new UnsupportedOperationException("Not supported yet.DUTDUI1");
	}
}
