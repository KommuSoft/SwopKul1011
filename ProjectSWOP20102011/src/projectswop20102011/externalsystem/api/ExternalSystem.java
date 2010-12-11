package projectswop20102011.externalsystem.api;

import be.kuleuven.cs.swop.api.IEvent;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.external.ExternalSystemException;
import be.kuleuven.cs.swop.external.IExternalSystem;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.controllers.TimeAheadController;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidWorldException;

public class ExternalSystem implements IExternalSystem {
	private Time currentTime;
	private TimeAheadController tac;
	private World world;

	public ExternalSystem(World world) throws InvalidWorldException {
		setCurrentTime(new Time(0,0));
		setWorld(world);
		tac = new TimeAheadController(world);
	}

	public Time getCurrentTime() {
		return currentTime;
	}

	private void setCurrentTime(Time currentTime) {
		if(isValidNewTime(currentTime)){
			this.currentTime = currentTime;
		} else {
			throw new IllegalArgumentException("The new time is not effective or before the current time.");
		}
	}

	private World getWorld(){
		return world;
	}

	private void setWorld(World world){
		this.world = world;
	}

	public void addEvent(IEvent e) throws InvalidWorldException{
		Event event = (Event) e;
		CreateEmergencyController cec = new CreateEmergencyController(getWorld());
		cec.addCreatedEmergencyToTheWorld(event.getEmergency());
	}

	@Override
	public void notifyTimeChanged(ITime time) throws ExternalSystemException, IllegalArgumentException {
		//TODO wannneer moet ExternalSystemException gesmeten worden
		Time t = (Time) time;
		if (isValidNewTime(t)) {
			try {
				tac.doTimeAheadAction(t.getHours() * 3600 + t.getMinutes() * 60);
				setCurrentTime((Time) time);
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
