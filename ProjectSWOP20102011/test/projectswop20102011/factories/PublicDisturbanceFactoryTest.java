package projectswop20102011.factories;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.SendableSeverity;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.PublicDisturbance;
import projectswop20102011.exceptions.InvalidAmountOfParametersException;
import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidParametersException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class PublicDisturbanceFactoryTest {

	private long x1;
	private long y1;
	private GPSCoordinate gps1, gps2;
	private SendableSeverity severity1, severity2;
	private String description1, description2;
	private long numberOfPeople1, numberOfPeople2;
	private PublicDisturbanceFactory pdf;

	@Before
	public void setUp() {
		x1 = 10;
		y1 = 10;

		gps1 = new GPSCoordinate(x1, y1);
		gps2 = null;
		severity1 = SendableSeverity.NORMAL;
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
	public void testCreateEmergency() throws InvalidEmergencyTypeNameException, InvalidLocationException, InvalidSendableSeverityException, NumberOutOfBoundsException, InvalidParametersException {
		pdf = new PublicDisturbanceFactory();
		PublicDisturbance pd  = (PublicDisturbance) pdf.createInstance(new Object[]{gps1, severity1, description1, numberOfPeople1});

		assertEquals(gps1, pd.getLocation());
		assertEquals(severity1, pd.getSeverity());
		assertEquals(description1, pd.getDescription());
		assertEquals(numberOfPeople1, pd.getNumberOfPeople());
	}

	@Test(expected = InvalidParametersException.class)
	public void testIllegalCreateEmergency() throws InvalidEmergencyTypeNameException, InvalidLocationException, InvalidSendableSeverityException, NumberOutOfBoundsException, InvalidParametersException {
		pdf = new PublicDisturbanceFactory();
		pdf.createInstance(new Object[]{});
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

		assertEquals("publicDisturbance", pdf.getName());
	}
}
