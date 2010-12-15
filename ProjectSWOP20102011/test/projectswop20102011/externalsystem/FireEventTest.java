package projectswop20102011.externalsystem;

import java.security.InvalidParameterException;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.World;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class FireEventTest {

	private Event event1, event2;
	private Time time1, time2;
	private Location location1;
	private Emergency emergency1;
	private World world;
	private int x1, y1;
	private EmergencySeverity severity1;
	private String description1;
	private FireSize size1;
	private boolean chemical1;
	private long trappedPeople1;
	private long numberOfInjured1;

	@Before
	public void setUp() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
		x1 = 10;
		y1 = 156;

		severity1 = EmergencySeverity.NORMAL;
		description1 = "Brand";
		size1 = FireSize.LOCAL;
		chemical1 = false;
		trappedPeople1 = 0;
		numberOfInjured1 = 123654;

		time1 = new Time(0, 10);
		time2 = new Time(0, 11);
		location1 = new Location(x1, y1);
		world = new World();
	}

	@Test
	public void testConstructor() throws InvalidWorldException, InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidEmergencyTypeNameException, InvalidParameterException {
		event1 = new FireEvent(time1, world, location1, severity1, description1, size1, chemical1, trappedPeople1, numberOfInjured1);
		assertEquals(0, event1.getTime().compareTo(time1));
		assertEquals("Fire", event1.getType());
		assertEquals(location1, event1.getLocation());
		assertEquals(severity1.getTextual(), event1.getSeverity());
	}

	//TODO test getEventProperties
	@Test
	public void testCompareTo() throws InvalidWorldException, InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidEmergencyTypeNameException, InvalidParameterException {
		event1 = new FireEvent(time1, world, location1, severity1, description1, size1, chemical1, trappedPeople1, numberOfInjured1);
		event2 = new FireEvent(time2, world, location1, severity1, description1, size1, chemical1, trappedPeople1, numberOfInjured1);

		assertTrue(event1.compareTo(event2) < 0);
	}
}
