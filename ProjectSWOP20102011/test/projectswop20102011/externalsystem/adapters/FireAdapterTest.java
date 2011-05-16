package projectswop20102011.externalsystem.adapters;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.Fire;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class FireAdapterTest {

	private Fire f1, f2;
	private FireAdapter fire1, fire2;
	private GPSCoordinate location1, location2;
	private EmergencySeverity severity1, severity2;
	private String description1, description2;
	private FireSize size1, size2;
	private boolean chemical1, chemical2;
	private long trappedPeople1, trappedPeople2;
	private long numberOfInjured1, numberOfInjured2;
	private TimeAdapter time;

	@Before
	public void setUp() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
		location1 = new GPSCoordinate(-10, 5);
		location2 = new GPSCoordinate(10, 66);

		severity1 = EmergencySeverity.BENIGN;
		severity2 = EmergencySeverity.SERIOUS;

		description1 = "Brand1";
		description2 = "Brand2";
		
		size1 = FireSize.FACILITY;
		size2 = FireSize.LOCAL;

		chemical1 = true;
		chemical2 = false;

		trappedPeople1 = 12;
		trappedPeople2 = 0;

		numberOfInjured1 = 0;
		numberOfInjured2 = 468;

		f1 = new Fire(location1, severity1, description1, size1, chemical1, trappedPeople1, numberOfInjured1);
		f2 = new Fire(location2, severity2, description2, size2, chemical2, trappedPeople2, numberOfInjured2);
		
		time = new TimeAdapter(1,1);
	}

	@Test
	public void testConstructor() {
		fire1 = new FireAdapter(f1);
		fire2 = new FireAdapter(f2, time);
		
		assertEquals(size1.toString(), fire1.getSize());
		assertEquals(size2.toString(), fire2.getSize());
		
		assertEquals(chemical1, fire1.isChemical());
		assertEquals(chemical2, fire2.isChemical());
		
		assertEquals(numberOfInjured1, fire1.getNumberOfInjured());
		assertEquals(numberOfInjured2, fire2.getNumberOfInjured());
		
		assertEquals(trappedPeople1, fire1.getNumberOfTrappedPeople());
		assertEquals(trappedPeople2, fire2.getNumberOfTrappedPeople());
		
		assertEquals(location1.getX(), fire1.getLocation().getX());
		assertEquals(location1.getY(), fire1.getLocation().getY());
		assertEquals(location2.getX(), fire2.getLocation().getX());
		assertEquals(location2.getY(), fire2.getLocation().getY());
		
		assertEquals(severity1.toString().toLowerCase(), fire1.getSeverity().toString().toLowerCase());
		assertEquals(severity2.toString().toLowerCase(), fire2.getSeverity().toString().toLowerCase());
		
		assertEquals(0, fire1.getTime().getHours());
		assertEquals(0, fire1.getTime().getMinutes());
		assertEquals(1, fire2.getTime().getHours());
		assertEquals(1, fire2.getTime().getMinutes());
		
		assertFalse(fire1.isPartOfDisaster());
		assertFalse(fire2.isPartOfDisaster());
		
		assertEquals(0 , fire1.getAssignedUnits().size());
		assertEquals(0 , fire2.getAssignedUnits().size());
		
		assertEquals("unhandled", fire1.getState().toString().toLowerCase());
		assertEquals("unhandled", fire2.getState().toString().toLowerCase());
	}
}
