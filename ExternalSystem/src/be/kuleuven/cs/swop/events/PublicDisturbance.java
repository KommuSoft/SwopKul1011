package be.kuleuven.cs.swop.events;

import be.kuleuven.cs.swop.api.ILocation;
import be.kuleuven.cs.swop.api.IPublicDisturbanceView;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.Severity;

public class PublicDisturbance extends Event implements IPublicDisturbanceView {

	private int people;
	
	public PublicDisturbance(ITime time, ILocation location, Severity severity, int people) {
		super(time, location, severity);
		this.setNumberOfPeople(people);
	}

	/*
	 * Getters and Setters
	 */

	@Override
	public int getNumberOfPeople() {
		return people;
	}

	private void setNumberOfPeople(int people) {
		this.people = people;
	}
}
