package projectswop20102011;

import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidCoordinateException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FireTest{
	private Fire f1;

	private EmergencyList el1;
	private GPSCoordinate gp1;
	private EmergencySeverity es1;
	private long id1;
	private FireSize fs1;
	private boolean chemical1;
	private boolean trappedPeople1;
	private long nmbOfInjured1;

	private long x1;
	private long y1;

	
	@Before
	public void setUp() throws InvalidCoordinateException{
		x1 = 10;
		y1 = 156;

		el1 = new EmergencyList();
		gp1 = new GPSCoordinate(x1, y1);
		es1 = EmergencySeverity.NORMAL;
		id1 = 42;
		fs1 = FireSize.LOCAL;
		chemical1 = false;
		trappedPeople1 = true;
		nmbOfInjured1 = 123654;
	}

	@Test
	public void testShortConstructor() throws InvalidEmergencyException{
		f1 = new Fire(el1, gp1, es1, fs1, chemical1, trappedPeople1, nmbOfInjured1);
		assertEquals(x1, f1.getLocation().getX());
		assertEquals(y1, f1.getLocation().getY());
		assertEquals(EmergencySeverity.NORMAL, f1.getSeverity());
		assertEquals(FireSize.LOCAL, f1.getSize());
		assertEquals(chemical1, f1.isChemical());
		assertEquals(trappedPeople1, f1.hasTrappedPeople());
		assertEquals(nmbOfInjured1, f1.getNumberOfInjured());
		assertEquals(1, el1.getEmergencies().size());
		assertEquals(f1, el1.getEmergencies().get(0));
	}

	@Test
	public void testExtendedConstructor() throws InvalidEmergencyException{
		f1 = new Fire(el1, gp1, es1, id1, fs1, chemical1, trappedPeople1, nmbOfInjured1);
		assertEquals(x1, f1.getLocation().getX());
		assertEquals(y1, f1.getLocation().getY());
		assertEquals(EmergencySeverity.NORMAL, f1.getSeverity());
		assertEquals(id1, f1.getId());
		assertEquals(FireSize.LOCAL, f1.getSize());
		assertEquals(chemical1, f1.isChemical());
		assertEquals(trappedPeople1, f1.hasTrappedPeople());
		assertEquals(nmbOfInjured1, f1.getNumberOfInjured());
		assertEquals(1, el1.getEmergencies().size());
		assertEquals(f1, el1.getEmergencies().get(0));
	}
}