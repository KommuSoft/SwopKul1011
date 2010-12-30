	package projectswop20102011.factories;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.Fire;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.exceptions.InvalidAmountOfParametersException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidParametersException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class FireFactoryTest {

	private long x1;
	private long y1;
	private GPSCoordinate gps1, gps2;
	private EmergencySeverity severity1, severity2;
	private String description1, description2;
	private FireSize size1, size2;
	private boolean chemical1;
	private long trappedPeople1, trappedPeople2;
	private long numberOfInjured1, numberOfInjured2;
	private FireFactory ff;

	@Before
	public void setUp(){
		x1 = 10;
		y1 = 10;

		gps1 = new GPSCoordinate(x1, y1);
		gps2 = null;
		severity1 = EmergencySeverity.NORMAL;
		severity2 = null;
		description1 = "BRAND!";
		description2 = null;
		size1 = FireSize.LOCAL;
		size2 = null;
		chemical1 = false;
		trappedPeople1 = 1;
		trappedPeople2 = -1;
		numberOfInjured1 = 3;
		numberOfInjured2 = -10;
	}

	@Test
	public void testConstructor() throws InvalidEmergencyTypeNameException{
		ff = new FireFactory();
	}

	@Test
	public void testCreateEmergency() throws InvalidEmergencyTypeNameException, InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidParametersException{
		ff = new FireFactory();
		Fire f = (Fire) ff.createEmergency(new Object[] {gps1, severity1, description1, size1, chemical1, trappedPeople1, numberOfInjured1});

		assertEquals(gps1, f.getLocation());
		assertEquals(severity1, f.getSeverity());
		assertEquals(description1, f.getDescription());
		assertEquals(size1, f.getSize());
		assertEquals(chemical1, f.isChemical());
		assertEquals(trappedPeople1, f.getTrappedPeople());
		assertEquals(numberOfInjured1, f.getNumberOfInjured());
	}

	@Test(expected = InvalidParametersException.class)
	public void testIllegalCreateEmergency() throws InvalidEmergencyTypeNameException, InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, InvalidAmountOfParametersException, NumberOutOfBoundsException, InvalidParametersException{
		ff = new FireFactory();
		ff.createEmergency(new Object[] {});
	}

	@Test
	public void testAreValidParameters() throws InvalidEmergencyTypeNameException {
		ff = new FireFactory();
		assertTrue(ff.areValidParameters(new Object[] {gps1, severity1, description1, size1, chemical1, trappedPeople1, numberOfInjured1}));
		assertFalse(ff.areValidParameters(new Object[] {gps2, severity1, description1, size1, chemical1, trappedPeople1, numberOfInjured1}));
		assertFalse(ff.areValidParameters(new Object[] {gps1, severity2, description1, size1, chemical1, trappedPeople1, numberOfInjured1}));
		assertFalse(ff.areValidParameters(new Object[] {gps1, severity1, description2, size1, chemical1, trappedPeople1, numberOfInjured1}));
		assertFalse(ff.areValidParameters(new Object[] {gps1, severity1, description1, size2, chemical1, trappedPeople1, numberOfInjured1}));
		assertTrue(ff.areValidParameters(new Object[] {gps1, severity1, description1, size1, chemical1, trappedPeople2, numberOfInjured1}));
		assertTrue(ff.areValidParameters(new Object[] {gps1, severity1, description1, size1, chemical1, trappedPeople1, numberOfInjured2}));
	}

	@Test
	public void testGetEmergencyTypeName() throws InvalidEmergencyTypeNameException{
		ff = new FireFactory();

		assertEquals("fire", ff.getEmergencyTypeName());
	}
}
