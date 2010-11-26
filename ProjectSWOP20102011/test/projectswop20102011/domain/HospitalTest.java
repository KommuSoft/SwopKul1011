package projectswop20102011.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;

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
    public void testValidConstructor() throws InvalidUnitBuildingNameException, InvalidLocationException{
		ziekenhuis1 = new Hospital(name1,location);
		assertEquals(x,ziekenhuis1.getHomeLocation().getX());
		assertEquals(y,ziekenhuis1.getHomeLocation().getY());
		assertEquals(name1,ziekenhuis1.getName());
	}

    @Test(expected = InvalidUnitBuildingNameException.class)
	public void testInvalidNameException() throws InvalidUnitBuildingNameException, InvalidLocationException{
		ziekenhuis2 = new Hospital(name2,location);
	}

	@Test(expected = InvalidLocationException.class)
	public void testInvalidLocationException() throws InvalidUnitBuildingNameException, InvalidLocationException{
		ziekenhuis2 = new Hospital(name1,null);
	}

}
