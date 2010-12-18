package projectswop20102011.domain.validators;

import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.MapItem;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;

public class AvailableUnitsMapItemEvaluationCriteriumTest {
	private AvailableUnitsMapItemEvaluationCriterium aumiec;
	private MapItem mi1, mi2;
	private String name1;
	private GPSCoordinate homeLocation1;
	private long speed1;
	private long x1, y1;

	@Before
	public void setUp() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException{
		name1 = "Vuurwagen";
		homeLocation1 = new GPSCoordinate(x1, y1);
		speed1 = 5;

		mi1 = new Firetruck(name1, homeLocation1, speed1);
		mi2 =  null;
		aumiec = new AvailableUnitsMapItemEvaluationCriterium();
	}

	@Test
	public void testIsValidMapItem(){
		assertTrue(aumiec.isValidMapItem(mi1));
		assertFalse((aumiec.isValidMapItem(mi2)));
	}
}