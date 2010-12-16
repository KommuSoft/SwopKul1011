package projectswop20102011.factories;

import java.security.InvalidParameterException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.PublicDisturbance;
import projectswop20102011.exceptions.InvalidAmountOfParametersException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class PublicDisturbanceFactoryTest {

	private long x1;
	private long y1;
	private GPSCoordinate gps1, gps2;
	private EmergencySeverity severity1, severity2;
	private String description1, description2;
	private long numberOfPeople1, numberOfPeople2;
	private PublicDisturbanceFactory pdf;

	@Before
	public void setUp() {
		x1 = 10;
		y1 = 10;

		gps1 = new GPSCoordinate(x1, y1);
		gps2 = null;
		severity1 = EmergencySeverity.NORMAL;
		severity2 = null;
		description1 = "Openbare dronkenschap";
		description2 = null;
		numberOfPeople1 = 3;
		numberOfPeople2 = -10;
	}

	@Test
	public void testConstructor() throws InvalidEmergencyTypeNameException {
		pdf = new PublicDisturbanceFactory();
	}

	@Test
	public void testCreateEmergency() throws InvalidEmergencyTypeNameException, InvalidAmountOfParametersException, InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
		pdf = new PublicDisturbanceFactory();
		PublicDisturbance pd  = (PublicDisturbance) pdf.createEmergency(new Object[]{gps1, severity1, description1, numberOfPeople1});

		assertEquals(gps1, pd.getLocation());
		assertEquals(severity1, pd.getSeverity());
		assertEquals(description1, pd.getDescription());
		assertEquals(numberOfPeople1, pd.getNumberOfPeople());
	}

	@Test(expected = InvalidAmountOfParametersException.class)
	public void testIllegalCreateEmergency() throws InvalidEmergencyTypeNameException, InvalidAmountOfParametersException, InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
		pdf = new PublicDisturbanceFactory();
		pdf.createEmergency(new Object[]{});
	}

	@Test
	public void testGetParameterClasses() throws InvalidEmergencyTypeNameException {
		pdf = new PublicDisturbanceFactory();
		Class[] c1 = {GPSCoordinate.class, EmergencySeverity.class, String.class, Long.class};

		assertEquals(c1.length, pdf.getParameterClasses().length);

		for (int i = 0; i < c1.length; ++i) {
			assertEquals(c1[i], pdf.getParameterClasses()[i]);
		}
	}

	@Test
	public void testAreValidParameters() throws InvalidEmergencyTypeNameException {
		pdf = new PublicDisturbanceFactory();
		assertTrue(pdf.areValidParameters(new Object[]{gps1, severity1, description1, numberOfPeople1}));
		assertFalse(pdf.areValidParameters(new Object[]{gps2, severity1, description1, numberOfPeople1}));
		assertFalse(pdf.areValidParameters(new Object[]{gps1, severity2, description1, numberOfPeople1}));
		assertFalse(pdf.areValidParameters(new Object[]{gps1, severity1, description2, numberOfPeople1}));
		assertTrue(pdf.areValidParameters(new Object[]{gps1, severity1, description1, numberOfPeople2}));
	}

	@Test
	public void testGetEmergencyTypeName() throws InvalidEmergencyTypeNameException {
		pdf = new PublicDisturbanceFactory();

		assertEquals("public disturbance", pdf.getEmergencyTypeName());
	}
}
