package projectswop20102011.externalsystem.api;

import be.kuleuven.cs.swop.api.ITime;

public class Time implements ITime{
	private final int hours;
	private final int minutes;

	public Time(int hours, int minutes){
		this.hours = hours;
		this.minutes = minutes;
	}

	@Override
	public int getHours() {
		return hours;
	}

	@Override
	public int getMinutes() {
		return minutes;
	}

	@Override
	public int compareTo(ITime o) {
		return getHours()*60+getMinutes()-o.getHours()*60+o.getMinutes();
	}

}