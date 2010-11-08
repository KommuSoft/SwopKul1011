package projectswop20102011;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class FireTest {

    private Fire f1;
	private UnitsNeeded un1;
    private GPSCoordinate gp1;
    private EmergencySeverity es1;
    private FireSize fs1, fs2, fs3;
    private boolean chemical1;
    private long trappedPeople1, trappedPeople2;
    private long nmbOfInjured1, nmbOfInjured2;
    private long x1;
    private long y1;

    @Before
    public void setUp(){
        x1 = 10;
        y1 = 156;

        gp1 = new GPSCoordinate(x1, y1);
        es1 = EmergencySeverity.NORMAL;
        fs1 = FireSize.LOCAL;
		fs2 = FireSize.HOUSE;
		fs3 = FireSize.FACILITY;
        chemical1 = false;
        trappedPeople1 = 0;
		trappedPeople2 = 1;
        nmbOfInjured1 = 123654;
		nmbOfInjured2 = 0;
    }

    @Test
    public void testConstructor() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
        f1 = new Fire(gp1, es1, fs1, chemical1, trappedPeople1, nmbOfInjured1);
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
        f1 = new Fire(null, es1, fs1, chemical1, trappedPeople1, nmbOfInjured1);
    }

    @Test(expected = InvalidEmergencySeverityException.class)
    public void testConstructorException2() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
        f1 = new Fire(gp1, null, fs1, chemical1, trappedPeople1, nmbOfInjured1);
    }

    @Test(expected = InvalidFireSizeException.class)
    public void testConstructorException3() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
        f1 = new Fire(gp1, es1, null, chemical1, trappedPeople1, nmbOfInjured1);
    }

    @Test(expected = NumberOutOfBoundsException.class)
    public void testConstructorException4() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
        f1 = new Fire(gp1, es1, fs1, chemical1, trappedPeople1, -42);
    }

	@Test
	public void testUnitsNeededLocalNoTrappedInjured() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException{
		f1 = new Fire(gp1, es1, fs1, chemical1, trappedPeople1, nmbOfInjured1);
		un1 = f1.calculateUnitsNeeded();

		boolean firetruck = false;
		long number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Firetruck.class){
				firetruck = true;
				number = un1.getNumbersNeeded()[i];
			}
		}
		assertTrue(firetruck);
		assertEquals(1, number);

		boolean ambulance = false;
		number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Ambulance.class){
				ambulance = true;
				number = un1.getNumbersNeeded()[i];
			}
		}

		assertTrue(ambulance);
		long ambulances = nmbOfInjured1;
		ambulances += trappedPeople1;
		assertEquals(ambulances, number);
	}

	@Test
	public void testUnitsNeededHouseNoTrappedInjured() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException{
		f1 = new Fire(gp1, es1, fs2, chemical1, trappedPeople1, nmbOfInjured1);
		un1 = f1.calculateUnitsNeeded();

		assertEquals(3, un1.getNumbersNeeded().length);
		assertEquals(3, un1.getUnits().length);

		boolean firetruck = false;
		long number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Firetruck.class){
				firetruck = true;
				number = un1.getNumbersNeeded()[i];
			}
		}
		assertTrue(firetruck);
		assertEquals(2, number);

		boolean policecar = false;
		number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Policecar.class){
				policecar = true;
				number = un1.getNumbersNeeded()[i];
			}
		}
		assertTrue(policecar);
		assertEquals(1, number);

		boolean ambulance = false;
		number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Ambulance.class){
				ambulance = true;
				number = un1.getNumbersNeeded()[i];
			}
		}
		assertTrue(ambulance);
		long ambulances = nmbOfInjured1;
		ambulances += trappedPeople1;
		assertEquals(ambulances, number);
	}

	@Test
	public void testUnitsNeededFacilityNoTrappedInjured() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException{
		f1 = new Fire(gp1, es1, fs3, chemical1, trappedPeople1, nmbOfInjured1);
		un1 = f1.calculateUnitsNeeded();

		assertEquals(3, un1.getNumbersNeeded().length);
		assertEquals(3, un1.getUnits().length);

		boolean firetruck = false;
		long number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Firetruck.class){
				firetruck = true;
				number = un1.getNumbersNeeded()[i];
			}
		}
		assertTrue(firetruck);
		assertEquals(4, number);

		boolean policecar = false;
		number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Policecar.class){
				policecar = true;
				number = un1.getNumbersNeeded()[i];
			}
		}
		assertTrue(policecar);
		assertEquals(3, number);

		boolean ambulance = false;
		number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Ambulance.class){
				ambulance = true;
				number = un1.getNumbersNeeded()[i];
			}
		}

		assertTrue(ambulance);
		long ambulances = nmbOfInjured1;
		ambulances += trappedPeople1;
		assertEquals(ambulances, number);
	}

	@Test
	public void testUnitsNeededLocalTrappedInjured() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException{
		f1 = new Fire(gp1, es1, fs1, chemical1, trappedPeople2, nmbOfInjured1);
		un1 = f1.calculateUnitsNeeded();

		boolean firetruck = false;
		long number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Firetruck.class){
				firetruck = true;
				number = un1.getNumbersNeeded()[i];
			}
		}

		assertTrue(firetruck);
		assertEquals(1, number);

		boolean ambulance = false;
		number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Ambulance.class){
				ambulance = true;
				number = un1.getNumbersNeeded()[i];
			}
		}

		assertTrue(ambulance);
		long ambulances = nmbOfInjured1;
		ambulances += trappedPeople2;
		assertEquals(ambulances, number);
	}

	@Test
	public void testUnitsNeededHouseTrappedInjured() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException{
		f1 = new Fire(gp1, es1, fs2, chemical1, trappedPeople2, nmbOfInjured1);
		un1 = f1.calculateUnitsNeeded();

		assertEquals(3, un1.getNumbersNeeded().length);
		assertEquals(3, un1.getUnits().length);

		boolean firetruck = false;
		long number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Firetruck.class){
				firetruck = true;
				number = un1.getNumbersNeeded()[i];
			}
		}
		assertTrue(firetruck);
		assertEquals(2, number);

		boolean policecar = false;
		number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Policecar.class){
				policecar = true;
				number = un1.getNumbersNeeded()[i];
			}
		}
		assertTrue(policecar);
		assertEquals(1, number);

		boolean ambulance = false;
		number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Ambulance.class){
				ambulance = true;
				number = un1.getNumbersNeeded()[i];
			}
		}

		assertTrue(ambulance);
		long ambulances = nmbOfInjured1;
		ambulances += trappedPeople2;
		assertEquals(ambulances, number);
	}

	@Test
	public void testUnitsNeededFacilityTrappedInjured() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException{
		f1 = new Fire(gp1, es1, fs3, chemical1, trappedPeople2, nmbOfInjured1);
		un1 = f1.calculateUnitsNeeded();

		assertEquals(3, un1.getNumbersNeeded().length);
		assertEquals(3, un1.getUnits().length);

		boolean firetruck = false;
		long number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Firetruck.class){
				firetruck = true;
				number = un1.getNumbersNeeded()[i];
			}
		}
		assertTrue(firetruck);
		assertEquals(4, number);

		boolean policecar = false;
		number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Policecar.class){
				policecar = true;
				number = un1.getNumbersNeeded()[i];
			}
		}
		assertTrue(policecar);
		assertEquals(3, number);

		boolean ambulance = false;
		number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Ambulance.class){
				ambulance = true;
				number = un1.getNumbersNeeded()[i];
			}
		}

		assertTrue(ambulance);
		long ambulances = nmbOfInjured1;
		ambulances += trappedPeople2;
		assertEquals(ambulances, number);
	}

	@Test
	public void testUnitsNeededLocalTrappedNoInjured() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException{
		f1 = new Fire(gp1, es1, fs1, chemical1, trappedPeople2, nmbOfInjured2);
		un1 = f1.calculateUnitsNeeded();

		boolean firetruck = false;
		long number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Firetruck.class){
				firetruck = true;
				number = un1.getNumbersNeeded()[i];
			}
		}
		assertTrue(firetruck);
		assertEquals(1, number);

		boolean ambulance = false;
		number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Ambulance.class){
				ambulance = true;
				number = un1.getNumbersNeeded()[i];
			}
		}

		assertTrue(ambulance);
		long ambulances = nmbOfInjured2;
		ambulances += trappedPeople2;
		assertEquals(ambulances, number);
	}

	@Test
	public void testUnitsNeededHouseTrappedNoInjured() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException{
		f1 = new Fire(gp1, es1, fs2, chemical1, trappedPeople2, nmbOfInjured2);
		un1 = f1.calculateUnitsNeeded();

		assertEquals(3, un1.getNumbersNeeded().length);
		assertEquals(3, un1.getUnits().length);

		boolean firetruck = false;
		long number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Firetruck.class){
				firetruck = true;
				number = un1.getNumbersNeeded()[i];
			}
		}
		assertTrue(firetruck);
		assertEquals(2, number);


		boolean policecar = false;
		number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Policecar.class){
				policecar = true;
				number = un1.getNumbersNeeded()[i];
			}
		}
		assertTrue(policecar);
		assertEquals(1, number);

		boolean ambulance = false;
		number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Ambulance.class){
				ambulance = true;
				number = un1.getNumbersNeeded()[i];
			}
		}

		assertTrue(ambulance);
		long ambulances = nmbOfInjured2;
		ambulances += trappedPeople2;
		assertEquals(ambulances, number);
	}

	@Test
	public void testUnitsNeededFacilityTrappedNoInjured() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException{
		f1 = new Fire(gp1, es1, fs3, chemical1, trappedPeople2, nmbOfInjured2);
		un1 = f1.calculateUnitsNeeded();

		assertEquals(3, un1.getNumbersNeeded().length);
		assertEquals(3, un1.getUnits().length);

		boolean firetruck = false;
		long number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Firetruck.class){
				firetruck = true;
				number = un1.getNumbersNeeded()[i];
			}
		}
		assertTrue(firetruck);
		assertEquals(4, number);

		boolean policecar = false;
		number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Policecar.class){
				policecar = true;
				number = un1.getNumbersNeeded()[i];
			}
		}
		assertTrue(policecar);
		assertEquals(3, number);

		boolean ambulance = false;
		number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Ambulance.class){
				ambulance = true;
				number = un1.getNumbersNeeded()[i];
			}
		}

		assertTrue(ambulance);
		long ambulances = nmbOfInjured2;
		ambulances += trappedPeople2;
		assertEquals(ambulances, number);
	}

	@Test
	public void testUnitsNeededLocalNoTrappedNoInjured() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException{
		f1 = new Fire(gp1, es1, fs1, chemical1, trappedPeople1, nmbOfInjured2);
		un1 = f1.calculateUnitsNeeded();

		boolean firetruck = false;
		long number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Firetruck.class){
				firetruck = true;
				number = un1.getNumbersNeeded()[i];
			}
		}
		assertTrue(firetruck);
		assertEquals(1, number);
	}

	@Test
	public void testUnitsNeededHouseNoTrappedNoInjured() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException{
		f1 = new Fire(gp1, es1, fs2, chemical1, trappedPeople1, nmbOfInjured2);
		un1 = f1.calculateUnitsNeeded();

		boolean firetruck = false;
		long number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Firetruck.class){
				firetruck = true;
				number = un1.getNumbersNeeded()[i];
			}
		}
		assertTrue(firetruck);
		assertEquals(2, number);

		boolean policecar = false;
		number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Policecar.class){
				policecar = true;
				number = un1.getNumbersNeeded()[i];
			}
		}
		assertTrue(policecar);
		assertEquals(1, number);
	}

	@Test
	public void testUnitsNeededFacilityNoTrappedNoInjured() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException{
		f1 = new Fire(gp1, es1, fs3, chemical1, trappedPeople1, nmbOfInjured2);
		un1 = f1.calculateUnitsNeeded();

		boolean firetruck = false;
		long number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Firetruck.class){
				firetruck = true;
				number = un1.getNumbersNeeded()[i];
			}
		}
		assertTrue(firetruck);
		assertEquals(4, number);

		boolean policecar = false;
		number = -1;
		for(int i=0; i<un1.getNumbersNeeded().length; ++i){
			if(un1.getUnits()[i] == Policecar.class){
				policecar = true;
				number = un1.getNumbersNeeded()[i];
			}
		}
		assertTrue(policecar);
		assertEquals(3, number);
	}
}