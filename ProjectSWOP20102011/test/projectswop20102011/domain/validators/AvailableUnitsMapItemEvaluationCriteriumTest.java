package projectswop20102011.domain.validators;

import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.MapItem;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.FireSize;
import projectswop20102011.exceptions.InvalidCapacityException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;

public class AvailableUnitsMapItemEvaluationCriteriumTest {
	private AvailableUnitsMapItemValidator aumiec;
	private MapItem mi1, mi2;
	private String name1;
	private GPSCoordinate homeLocation1;
	private long speed1;
	private long x1, y1;
	private long capacity;

	@Before
	public void setUp() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException{
		name1 = "Vuurwagen";
		homeLocation1 = new GPSCoordinate(x1, y1);
		speed1 = 5;
		capacity = 1000;

		mi1 = new Firetruck(name1, homeLocation1, speed1, capacity);
		mi2 =  null;
		aumiec = new AvailableUnitsMapItemValidator();
	}

	@Test
	public void testIsValidMapItem(){
		assertTrue(aumiec.isValid(mi1));
		assertFalse((aumiec.isValid(mi2)));
	}
}
