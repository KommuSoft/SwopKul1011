package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.api.IEmergencyDispatchApi;
import be.kuleuven.cs.swop.api.IEvent;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.external.ExternalSystemException;
import be.kuleuven.cs.swop.external.IExternalSystem;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.controllers.TimeAheadController;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidWorldException;

public class ExternalSystem implements IExternalSystem {

	private Time currentTime;
	private TimeAheadController tac;
	private EmergencyDispatchApi emergencyDispatchApi;
	
	public ExternalSystem(IEmergencyDispatchApi emergencyDispatchApi) throws InvalidWorldException{
		setEmergencyDispatchApi((EmergencyDispatchApi) emergencyDispatchApi);
		setCurrentTime(new Time(0, 0));
		setTimeAheadController(new TimeAheadController(getEmergencyDispatchApi().getWorld()));
	}

	public static IExternalSystem bootstrap(IEmergencyDispatchApi emergencyDispatchApi) {
		try {
			return new ExternalSystem(emergencyDispatchApi);
		} catch (InvalidWorldException ex) {
			Logger.getLogger(ExternalSystem.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	private EmergencyDispatchApi getEmergencyDispatchApi(){
		return emergencyDispatchApi;
	}

	private void setEmergencyDispatchApi(EmergencyDispatchApi emergencyDispatchApi){
		this.emergencyDispatchApi = emergencyDispatchApi;
	}

	public Time getCurrentTime() {
		return currentTime;
	}

	private void setCurrentTime(Time currentTime) {
		if (isValidNewTime(currentTime)) {
			this.currentTime = currentTime;
		} else {
			throw new IllegalArgumentException("The new time is not effective or before the current time.");
		}
	}

	private TimeAheadController getTimeAheadController(){
		return tac;
	}

	private void setTimeAheadController(TimeAheadController tac){
		this.tac = tac;
	}

	@Override
	public void notifyTimeChanged(ITime time) throws ExternalSystemException, IllegalArgumentException {
		//TODO wannneer moet ExternalSystemException gesmeten worden
		Time t = (Time) time;
		if (isValidNewTime(t)) {
			try {
				getTimeAheadController().doTimeAheadAction(t.getHours() * 3600 + t.getMinutes() * 60);
				setCurrentTime((Time) time);
			} catch (InvalidDurationException ex) {
				Logger.getLogger(ExternalSystem.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			throw new IllegalArgumentException("The new time is not effective or before the current time.");
		}
	}

	/**
	 * Checks if the given time is a valid new time for this ExternalSystem
	 * @param time
	 *		The time to check.
	 * @return True if the given time is a valid new time; false otherwise.
	 *		A valid new time is a time that is not null and that is after the current time.
	 */
	public boolean isValidNewTime(Time time) {
		return (time != null) && (getCurrentTime().compareTo(time) < 0);
	}
}
