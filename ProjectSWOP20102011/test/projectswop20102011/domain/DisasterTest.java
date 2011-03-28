package projectswop20102011.domain;

import org.junit.Before;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class DisasterTest {

    private Emergency e1, e2;
    private GPSCoordinate l1, l2;
    private long x1, y1, x2, y2;
	private Disaster d;

    @Before
    public void setUp() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException{
        x1 = 12;
        y1 = 789;
        x2 = 95;
        y2 = 65;
        l1 = new GPSCoordinate(x1, y1);
        l2 = new GPSCoordinate(x2, y2);

        e1 = new Fire(l1, EmergencySeverity.URGENT, "", FireSize.LOCAL, false, 0, 1337);
        e2 = new PublicDisturbance(l1, EmergencySeverity.URGENT, "", 1302);

		d = new Disaster(new ArrayList<Emergency>())
    }
}
