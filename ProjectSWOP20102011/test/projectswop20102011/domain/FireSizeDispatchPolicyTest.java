package projectswop20102011.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class FireSizeDispatchPolicyTest {

	private Emergency e1;
	private long xEmergency, yEmergency, x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6, x7, y7, x8, y8, x9, y9;
	private GPSCoordinate locationEmergency, location1, location2, location3, location4, location5, location6, location7, location8, location9, location10;
	private EmergencySeverity severity;
	private String description;
	private FireSize firesize, firetrucksize1, firetrucksize2, firetrucksize3, firetrucksize4, firetrucksize5, firetrucksize6, firetrucksize7, firetrucksize8, firetrucksize9;
	private boolean chemical;
	private long trappedPeople, numberOfInjured;
	private String name1, name2, name3, name4, name5, name6, name7, name8, name9;
	private long speed1, speed2, speed3, speed4, speed5, speed6, speed7, speed8, speed9;
	private Unit u1, u2, u3, u4, u5, u6, u7, u8, u9;

	@Before
	public void setUp() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidEmergencySeverityException, NumberOutOfBoundsException, InvalidFireSizeException {

		xEmergency = 0;
		yEmergency = 0;
		locationEmergency = new GPSCoordinate(xEmergency, yEmergency);
		severity = EmergencySeverity.BENIGN;
		description = "Vuurtje";
		firesize = FireSize.LOCAL;
		chemical = false;
		trappedPeople = 0;
		numberOfInjured = 0;
		e1 = new Fire(locationEmergency, severity, description, firesize, chemical, trappedPeople, numberOfInjured);


		name1 = "FT1";
		x1 = 0;
		y1 = 0;
		location1 = new GPSCoordinate(x1, y1);
		speed1 = 5;
		firetrucksize1 = FireSize.LOCAL;
		u1 = new Firetruck(name1, location1, speed1, firetrucksize1);

		name2 = "FT2";
		x2 = 10;
		y2 = 10;
		location2 = new GPSCoordinate(x2, y2);
		speed2 = 10;
		firetrucksize2 = FireSize.HOUSE;
		u2 = new Firetruck(name2, location2, speed2, firetrucksize2);

		name3 = "FT3";
		x3 = 20;
		y3 = 20;
		location3 = new GPSCoordinate(x3, y3);
		speed3 = 50;
		firetrucksize3 = FireSize.FACILITY;
		u3 = new Firetruck(name3, location3, speed3, firetrucksize3);

		name4 = "FT4";
		x4 = 0;
		y4 = 0;
		location4 = new GPSCoordinate(x4, y4);
		speed4 = 2;
		firetrucksize4 = FireSize.LOCAL;
		u4 = new Firetruck(name4, location4, speed4, firetrucksize4);

		name5 = "FT5";
		x5 = 100;
		y5 = 100;
		location5 = new GPSCoordinate(x5, y5);
		speed4 = 10;
		firetrucksize5 = FireSize.FACILITY;
		u5 = new Firetruck(name5, location5, speed5, firetrucksize5);

		name6 = "FT6";
		x6 = 100;
		y6 = 100;
		location6 = new GPSCoordinate(x6, y6);
		speed6 = 100;
		firetrucksize6 = FireSize.FACILITY;
		u6 = new Firetruck(name6, location6, speed6, firetrucksize6);

		name7 = "FT7";
		x7 = 1;
		y7 = 1;
		location7 = new GPSCoordinate(x7, y7);
		speed7 = 200;
		firetrucksize7 = FireSize.HOUSE;
		u7 = new Firetruck(name7, location7, speed7, firetrucksize7);

		name8 = "FT8";
		x8 = 150;
		y8 = -20;
		location8 = new GPSCoordinate(x8, y8);
		speed8 = 200;
		firetrucksize8 = FireSize.LOCAL;
		u8 = new Firetruck(name8, location8, speed8, firetrucksize8);

		name9 = "FT9";
		x9 = 150;
		y9 = -20;
		location9 = new GPSCoordinate(x9, y9);
		speed9 = 200;
		firetrucksize9 = FireSize.HOUSE;
		u9 = new Firetruck(name9, location9, speed9, firetrucksize9);


	}

	@Test
	//u1 bevindt zich al op de locatie van emergency (is dus altijd eerst in dit
	//geval) en zijn capaciteit benadert de firesize van de emergency ook beter
	public void compareTest1() {
		assertTrue(e1.getDispatchPolicy().compare(u1, u2) < 0);
		assertTrue(e1.getDispatchPolicy().compare(u1, u1) == 0);
		assertTrue(e1.getDispatchPolicy().compare(u2, u1) > 0);
		assertTrue(e1.getDispatchPolicy().compare(u2, u2) == 0);
	}

	@Test
	//Analoog aan compareTest1
	public void compareTest2() {
		assertTrue(e1.getDispatchPolicy().compare(u4, u3) < 0);
		assertTrue(e1.getDispatchPolicy().compare(u3, u3) == 0);
		assertTrue(e1.getDispatchPolicy().compare(u3, u4) > 0);
		assertTrue(e1.getDispatchPolicy().compare(u4, u4) == 0);
	}

	@Test
	//u1 en u4 bevinden zich beiden al op de plaats van de emergency en hebben
	//dezelfde capaciteit
	public void compareTest3() {
		assertTrue(e1.getDispatchPolicy().compare(u1, u4) == 0);
		assertTrue(e1.getDispatchPolicy().compare(u1, u1) == 0);
		assertTrue(e1.getDispatchPolicy().compare(u1, u4) == 0);
		assertTrue(e1.getDispatchPolicy().compare(u4, u1) == 0);
	}

	@Test
	//u5 en u6 bevinden zich op dezelfde locatie (niet de emergencylocatie),
	//hebben dezelfde capaciteit, maar de snelheid van u6 ligt hoger.
	public void compareTest4() {
		assertTrue(e1.getDispatchPolicy().compare(u6, u5) < 0);
		assertTrue(e1.getDispatchPolicy().compare(u5, u5) == 0);
		assertTrue(e1.getDispatchPolicy().compare(u5, u6) > 0);
		assertTrue(e1.getDispatchPolicy().compare(u6, u6) == 0);
	}

	@Test
	//u7 benadert de capaciteit minder goed, maar zal er veel rapper zijn.
	public void compareTest5() {
		assertTrue(e1.getDispatchPolicy().compare(u7, u8) < 0);
		assertTrue(e1.getDispatchPolicy().compare(u7, u7) == 0);
		assertTrue(e1.getDispatchPolicy().compare(u8, u7) > 0);
		assertTrue(e1.getDispatchPolicy().compare(u8, u8) == 0);
	}

	@Test
	//u8 en u9 hebben dezelfde snelheid en bevinden zich evenver van de emergency,
	//maar de capaciteit van u8 benadert beter
	public void compareTest6() {
		assertTrue(e1.getDispatchPolicy().compare(u8, u9) < 0);
		assertTrue(e1.getDispatchPolicy().compare(u8, u8) == 0);
		assertTrue(e1.getDispatchPolicy().compare(u9, u8) > 0);
		assertTrue(e1.getDispatchPolicy().compare(u9, u9) == 0);
	}
}
