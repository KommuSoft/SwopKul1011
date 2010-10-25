package projectswop20102011;

import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidCoordinateException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PublicDisturbanceTest{
	private PublicDisturbance pd1;

	private EmergencyList el1;
	private GPSCoordinate gp1;
	private EmergencySeverity es1;
	private long nmbOfPeople1;

	private long x1;
	private long y1;

	@Before
	public void setUp() throws InvalidCoordinateException{
		x1 = 3141592;
		y1 = 2718281;

		el1 = new EmergencyList();
		gp1 = new GPSCoordinate(x1, y1);
		es1 = EmergencySeverity.SERIOUS;
		nmbOfPeople1 = 666;
	}

	@Test
	public void testShortConstructor() throws InvalidEmergencyException{
		pd1 = new PublicDisturbance(el1, gp1, es1, nmbOfPeople1);
		assertEquals(x1, pd1.getLocation().getX());
		assertEquals(y1, pd1.getLocation().getY());
		assertEquals(EmergencySeverity.SERIOUS, pd1.getSeverity());
		assertEquals(nmbOfPeople1, pd1.getNumberOfPeople());
		assertEquals(1, el1.getEmergencies().size());
		assertEquals(pd1, el1.getEmergencies().get(0));
	}

	@Test
	public void extendedConstructor() throws InvalidEmergencyException{
		pd1 = new PublicDisturbance(el1, gp1, es1, nmbOfPeople1);
		assertEquals(x1, pd1.getLocation().getX());
		assertEquals(y1, pd1.getLocation().getY());
		assertEquals(EmergencySeverity.SERIOUS, pd1.getSeverity());
		assertEquals(nmbOfPeople1, pd1.getNumberOfPeople());
		assertEquals(1, el1.getEmergencies().size());
		assertEquals(pd1, el1.getEmergencies().get(0));
	}

}