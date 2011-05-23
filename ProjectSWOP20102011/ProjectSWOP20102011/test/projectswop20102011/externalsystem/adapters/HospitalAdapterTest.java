package projectswop20102011.externalsystem.adapters;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Hospital;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;

public class HospitalAdapterTest {

	private Hospital h1, h2;
	private HospitalAdapter hospital1, hospital2;
	private String name1, name2;
	private GPSCoordinate location1, location2;

	@Before
	public void setUp() throws InvalidMapItemNameException, InvalidLocationException {
		name1 = "Ziekenhuis1";
		name2= "Ziekenhuis2";
		
		location1 = new GPSCoordinate(10, -90);
		location2 = new GPSCoordinate(6,2);
		
		h1 = new Hospital(name1, location1);
		h2 = new Hospital(name2, location2);
	}

	@Test
	public void testConstructor() {
		hospital1 = new HospitalAdapter(h1);
		hospital2 = new HospitalAdapter(h2);
		
		assertEquals(name1, hospital1.getName());
		assertEquals(name2, hospital2.getName());
		
		assertEquals(location1.getX(), hospital1.getLocation().getX());
		assertEquals(location1.getY(), hospital1.getLocation().getY());
		assertEquals(location2.getX(), hospital2.getLocation().getX());
		assertEquals(location2.getY(), hospital2.getLocation().getY());
	}
}
