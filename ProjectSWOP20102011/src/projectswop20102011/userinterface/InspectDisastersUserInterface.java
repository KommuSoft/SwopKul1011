package projectswop20102011.userinterface;

import projectswop20102011.controllers.Controller;
import projectswop20102011.controllers.DisasterMapper;
import projectswop20102011.controllers.InspectDisastersController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;

/**
 * A command user interface to inspect the disasters
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InspectDisastersUserInterface extends CommandUserInterface{

	private final InspectDisastersController inspectDisastersController;
	private final DisasterMapper disasterController;

	public InspectDisastersUserInterface(InspectDisastersController inspectDisastersController, DisasterMapper disasterController) throws InvalidCommandNameException, InvalidControllerException {
		super("inspect disasters");

		if (inspectDisastersController == null || disasterController == null) {
			throw new InvalidControllerException("Controller must be effective.");
		}
		this.inspectDisastersController = inspectDisastersController;
		this.disasterController = disasterController;
	}

	@Override
	public Controller getController() {
		return this.inspectDisastersController;
	}

	private DisasterMapper getDisasterController() {
		return disasterController;
	}

	@Override
	public void handleUserInterface() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
