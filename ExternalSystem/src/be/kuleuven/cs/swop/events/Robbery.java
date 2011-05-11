package be.kuleuven.cs.swop.events;

import be.kuleuven.cs.swop.api.ILocation;
import be.kuleuven.cs.swop.api.IRobberyView;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.Severity;

public class Robbery extends Event implements IRobberyView {

	private boolean inProgress;
	private boolean armed;
	
	public Robbery(ITime time, ILocation location, Severity severity, boolean armed, boolean inProgress) {
		super(time, location, severity);
		this.setArmed(armed);
		this.setInProgress(inProgress);
	}

	/*
	 * Getters and Setters
	 */
	
	@Override
	public boolean isArmed() {
		return armed;
	}
	
	private void setArmed(boolean armed) {
		this.armed = armed;
	}

	@Override
	public boolean isInProgress() {
		return inProgress;
	}
	
	private void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}
}