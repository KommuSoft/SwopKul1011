package be.kuleuven.cs.swop.events;

import be.kuleuven.cs.swop.api.ILocation;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.ITrafficAccidentView;
import be.kuleuven.cs.swop.api.Severity;

public class TrafficAccident extends Event implements ITrafficAccidentView {

	private int cars;
	private int injured;
	
	public TrafficAccident(ITime time, ILocation location, Severity severity, int cars, int injured) {
		super(time, location, severity);
		this.setNumberOfCars(cars);
		this.setNumberOfInjured(injured);
	}
	
	/*
	 * Getters and Setters
	 */
	
	@Override
	public int getNumberOfCars() {
		return cars;
	}
	
	private void setNumberOfCars(int cars) {
		this.cars = cars;
	}

	@Override
	public int getNumberOfInjured() {
		return injured;
	}
	
	private void setNumberOfInjured(int injured) {
		this.injured = injured;
	}

}
