package projectswop20102011.factories;

import java.security.InvalidParameterException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Robbery;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;

public class RobberyFactoryTest {

	private long x1;
	private long y1;
	private GPSCoordinate gps1, gps2;
	private EmergencySeverity severity1, severity2;
	private String description1, description2;
	boolean armed1;
	boolean inProgress1;
	private RobberyFactory rf;

	@Before
	public void setUp() {
		x1 = 10;
		y1 = 10;

		gps1 = new GPSCoordinate(x1, y1);
		gps2 = null;
		severity1 = EmergencySeverity.NORMAL;
		severity2 = null;
		description1 = "Diefstal";
		description2 = null;
		armed1 = true;
		inProgress1 = false;
	}


	@Test
	public void testConstructor() throws InvalidEmergencyTypeNameException {
		rf = new RobberyFactory();
	}

	@Test
	public void testCreateEmergency() throws InvalidEmergencyTypeNameException {
		rf = new RobberyFactory();
		Robbery r  = (Robbery) rf.createEmergency(new Object[]{gps1, severity1, description1, armed1, inProgress1});

		assertEquals(gps1, r.getLocation());
		assertEquals(severity1, r.getSeverity());
		assertEquals(description1, r.getDescription());
		assertEquals(armed1, r.isArmed());
		assertEquals(inProgress1, r.isInProgress());
	}

	@Test(expected = InvalidParameterException.class)
	public void testIllegalCreateEmergency() throws InvalidEmergencyTypeNameException {
		rf = new RobberyFactory();
		rf.createEmergency(new Object[]{});
	}

	@Test
	public void testGetParameterClasses() throws InvalidEmergencyTypeNameException {
		rf = new RobberyFactory();
		Class[] c1 = {GPSCoordinate.class, EmergencySeverity.class, String.class, Boolean.class, Boolean.class};

		assertEquals(c1.length, rf.getParameterClasses().length);

		for (int i = 0; i < c1.length; ++i) {
			assertEquals(c1[i], rf.getParameterClasses()[i]);
		}
	}

	@Test
	public void testAreValidParameters() throws InvalidEmergencyTypeNameException {
		rf = new RobberyFactory();
		assertTrue(rf.areValidParameters(new Object[]{gps1, severity1, description1, armed1, inProgress1}));
		assertFalse(rf.areValidParameters(new Object[]{gps2, severity1, description1, armed1, inProgress1}));
		assertFalse(rf.areValidParameters(new Object[]{gps1, severity2, description1, armed1, inProgress1}));
		assertFalse(rf.areValidParameters(new Object[]{gps1, severity1, description2, armed1, inProgress1}));
	}

	@Test
	public void testGetEmergencyTypeName() throws InvalidEmergencyTypeNameException {
		rf = new RobberyFactory();

		assertEquals("robbery", rf.getEmergencyTypeName());
	}
}
