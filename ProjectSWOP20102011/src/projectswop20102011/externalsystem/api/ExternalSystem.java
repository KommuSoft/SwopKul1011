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
	private Time currentTime;
	private TimeAheadController tac;

	public ExternalSystem(World world) throws InvalidWorldException {
		currentTime = new Time(0, 0);
		tac = new TimeAheadController(world);
	}

	public Time getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Time currentTime) {
		this.currentTime = currentTime;
	}

	@Override
	public void notifyTimeChanged(ITime time) throws ExternalSystemException, IllegalArgumentException {
		//TODO wannneer moet ExternalSystemException gesmeten worden
		Time t = (Time) time;
		if (isValidNewTime(t)) {
			try {
				tac.doTimeAheadAction(t.getHours() * 3600 + t.getMinutes() * 60);
			} catch (InvalidDurationException ex) {
				Logger.getLogger(ExternalSystem.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			throw new IllegalArgumentException("The new time is not effective or before the current time.");
		}
	}

	public boolean isValidNewTime(Time time) {
		return (time != null) && (getCurrentTime().compareTo(time) < 0);
	}
}
