package projectswop20102011.userinterface;

import projectswop20102011.controllers.Controller;
import projectswop20102011.controllers.CreateDisasterController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;

public class CreateDisasterUserInterface extends CommandUserInterface {

	private final CreateDisasterController createDisasterController;

	public CreateDisasterUserInterface(CreateDisasterController createDisasterController) throws InvalidCommandNameException, InvalidControllerException {
		super("create disaster");

		if (createDisasterController == null) {
			throw new InvalidControllerException("Controller must be effective.");
		}
		this.createDisasterController = createDisasterController;
	}

	@Override
	public Controller getController() {
		return this.createDisasterController;
	}

	@Override
	public void handleUserInterface() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
