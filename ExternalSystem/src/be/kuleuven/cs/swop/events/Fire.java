package be.kuleuven.cs.swop.events;

import be.kuleuven.cs.swop.api.IFireView;
import be.kuleuven.cs.swop.api.ILocation;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.Severity;

public class Fire extends Event implements IFireView {

	private String size = null;
	private boolean chemical;
	private int trapped = -1;
	private int injured = -1;
	
	public Fire(ITime time, ILocation location, Severity severity, String size, boolean chemical, int trapped, int injured) {
		super(time, location, severity);
		setSize(size);
		setChemical(chemical);
		setNumberOfTrappedPeople(trapped);
		setNumberOfInjured(injured);
	}
	
	/*
	 * Getters and Setters
	 */

	@Override
	public String getSize() {
		return size;
	}
	
	private void setSize(String size) {
		this.size = size;
	}

	@Override
	public boolean isChemical() {
		return chemical;
	}
	
	private void setChemical(boolean chemical) {
		this.chemical = chemical;
	}

	@Override
	public int getNumberOfTrappedPeople() {
		return trapped;
	}
	
	private void setNumberOfTrappedPeople(int trapped) {
		this.trapped = trapped;
	}

	@Override
	public int getNumberOfInjured() {
		return injured;
	}
	
	private void setNumberOfInjured(int injured) {
		this.injured = injured;
	}

}
