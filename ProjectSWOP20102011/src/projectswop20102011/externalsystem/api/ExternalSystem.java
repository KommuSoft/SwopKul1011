package projectswop20102011.externalsystem.api;

import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.external.ExternalSystemException;
import be.kuleuven.cs.swop.external.IExternalSystem;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.controllers.TimeAheadController;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidWorldException;

public class ExternalSystem implements IExternalSystem {
	private TimeAheadController tac;

	public ExternalSystem(World world) throws InvalidWorldException {
		tac = new TimeAheadController(world);
	}

	@Override
	public void notifyTimeChanged(ITime time) throws ExternalSystemException, IllegalArgumentException {
		//TODO throw errors

		try {
			tac.doTimeAheadAction(time.getHours() * 3600 + time.getMinutes() * 60);
		} catch (InvalidDurationException ex) {
			Logger.getLogger(ExternalSystem.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
