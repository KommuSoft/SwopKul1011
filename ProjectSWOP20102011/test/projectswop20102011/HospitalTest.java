package projectswop20102011;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidHospitalNameException;
import projectswop20102011.exceptions.InvalidLocationException;

public class HospitalTest {

	private GPSCoordinate location;
	private long x,y;
	private String name1,name2;
	private Hospital ziekenhuis1,ziekenhuis2;

    @Before
    public void setUp(){
		x = 11;
		y = 4;
		location = new GPSCoordinate(x,y);
		name1 = "De Kliniek";
		name2 = "";
	}

	@Test
    public void testValidConstructor() throws InvalidLocationException, InvalidHospitalNameException{
		ziekenhuis1 = new Hospital(location,name1);
		assertEquals(x,ziekenhuis1.getLocation().getX());
		assertEquals(y,ziekenhuis1.getLocation().getY());
		assertEquals(name1,ziekenhuis1.getName());
	}

    @Test(expected = InvalidHospitalNameException.class)
	public void testInvalidNameException() throws InvalidLocationException, InvalidHospitalNameException{
		ziekenhuis2 = new Hospital(location,name2);
	}

	@Test(expected = InvalidLocationException.class)
	public void testInvalidLocationException() throws InvalidLocationException, InvalidHospitalNameException{
		ziekenhuis2 = new Hospital(null,name1);
	}

}
