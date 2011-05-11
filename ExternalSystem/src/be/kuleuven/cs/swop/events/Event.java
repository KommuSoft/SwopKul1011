package be.kuleuven.cs.swop.events;

import be.kuleuven.cs.swop.api.IEvent;
import be.kuleuven.cs.swop.api.ILocation;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.Severity;

/**
 * A concrete implementation of the IEvent interface
 * @author philippe
 *
 */
public abstract class Event implements IEvent {

	/**
	 * The time at which the event occurred.
	 */
	private ITime time = null;
	/**
	 * The location at which the event occurred
	 */
	private ILocation location = null;
	/**
	 * The severity of the event
	 */
	private Severity severity = null;
	
	/**
	 * Creates a new Event with the given time and location
	 * @param time The time at which the event occurred
	 * @param location The location at which the event occurred
	 * @param severity The severity of the event
	 */
	public Event(ITime time, ILocation location, Severity severity) {
		setTime(time);
		setLocation(location);
		setSeverity(severity);
	}

	@Override
	public boolean equals(Object arg0) {
		if(arg0 == null)
			return false;
		
		try {
			Event e = (Event)arg0;
			return getTime().equals(e.getTime()) && getLocation().equals(e.getLocation()) && getSeverity().equals(e.getSeverity());
		}
		catch(ClassCastException e) {
			return false;
		}
	}
	
	/*
	 * Getters and Setters
	 */
	
	@Override
	public ITime getTime() {
		return time;
	}
	
	/** 
	 * Sets a new time value
	 * @param time The time to set
	 */
	private void setTime(ITime time) {
		this.time = time;
	}

	@Override
	public ILocation getLocation() {
		return location;
	}
	
	/** 
	 * Sets a new location value
	 * @param location The location to set
	 */
	private void setLocation(ILocation location) {
		this.location = location;
	}

	@Override
	public Severity getSeverity() {
		return severity;
	}
	
	/** 
	 * Sets a new severity value
	 * @param severity The severity to set
	 */
	private void setSeverity(Severity severity) {
		this.severity = severity;
	}

}
