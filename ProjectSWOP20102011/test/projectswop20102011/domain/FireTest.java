package projectswop20102011.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class FireTest {

    private Fire f1;
    private GPSCoordinate gp1;
    private EmergencySeverity es1;
    private FireSize fs1;
    private boolean chemical1;
    private long trappedPeople1;
    private long nmbOfInjured1;
    private long x1;
    private long y1;

    @Before
    public void setUp(){
        x1 = 10;
        y1 = 156;

        gp1 = new GPSCoordinate(x1, y1);
        es1 = EmergencySeverity.NORMAL;
        fs1 = FireSize.LOCAL;
        chemical1 = false;
        trappedPeople1 = 0;
        nmbOfInjured1 = 123654;
    }

    @Test
    public void testConstructor() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
        f1 = new Fire(gp1, es1, "", fs1, chemical1, trappedPeople1, nmbOfInjured1);
        assertEquals(x1, f1.getLocation().getX());
        assertEquals(y1, f1.getLocation().getY());
        assertEquals(EmergencySeverity.NORMAL, f1.getSeverity());
        assertEquals(FireSize.LOCAL, f1.getSize());
        assertEquals(chemical1, f1.isChemical());
        assertEquals(trappedPeople1, f1.getTrappedPeople());
        assertEquals(nmbOfInjured1, f1.getNumberOfInjured());
    }

    @Test(expected = InvalidLocationException.class)
    public void testConstructorException1() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
        f1 = new Fire(null, es1, "", fs1, chemical1, trappedPeople1, nmbOfInjured1);
    }

    @Test(expected = InvalidEmergencySeverityException.class)
    public void testConstructorException2() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
        f1 = new Fire(gp1, null, "", fs1, chemical1, trappedPeople1, nmbOfInjured1);
    }

    @Test(expected = InvalidFireSizeException.class)
    public void testConstructorException3() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
        f1 = new Fire(gp1, es1, "", null, chemical1, trappedPeople1, nmbOfInjured1);
    }

    @Test(expected = NumberOutOfBoundsException.class)
    public void testConstructorException4() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
        f1 = new Fire(gp1, es1, "", fs1, chemical1, -42, nmbOfInjured1);
    }

    @Test(expected = NumberOutOfBoundsException.class)
    public void testConstructorException5() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
        f1 = new Fire(gp1, es1, "", fs1, chemical1, trappedPeople1, -42);
    }
}