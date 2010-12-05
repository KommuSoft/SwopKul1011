package projectswop20102011.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;

public class HospitalTest {

	private GPSCoordinate location;
	private long x,y;
	private String name1,name2;
	private Hospital ziekenhuis;

    @Before
    public void setUp(){
		x = 11;
		y = 4;
		location = new GPSCoordinate(x,y);
		name1 = "De Kliniek";
		name2 = "";
	}

	@Test
    public void testValidConstructor() throws InvalidMapItemNameException, InvalidLocationException{
		ziekenhuis = new Hospital(name1,location);
		assertEquals(x,ziekenhuis.getHomeLocation().getX());
		assertEquals(y,ziekenhuis.getHomeLocation().getY());
		assertEquals(name1,ziekenhuis.getName());
	}

    @Test(expected = InvalidMapItemNameException.class)
	public void testInvalidNameException() throws InvalidMapItemNameException, InvalidLocationException{
		ziekenhuis = new Hospital(name2,location);
	}

	@Test(expected = InvalidLocationException.class)
	public void testInvalidLocationException() throws InvalidMapItemNameException, InvalidLocationException{
		ziekenhuis = new Hospital(name1,null);
	}

}
