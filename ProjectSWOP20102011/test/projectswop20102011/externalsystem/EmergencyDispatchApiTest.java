package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.api.EmergencyDispatchException;
import java.security.InvalidParameterException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidAmountOfParametersException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class EmergencyDispatchApiTest {

	private World world;
	private EmergencyDispatchApi emergencyDispatchApi;
	private Event event1;
	private Time time1;
	private Location location1;
	private int x1, y1;
	private EmergencySeverity severity1;
	private String description1;
	private FireSize size1;
	private boolean chemical1;
	private long trappedPeople1;
	private long numberOfInjured1;

	@Before
	public void setUp() throws InvalidWorldException, InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidEmergencyTypeNameException, InvalidParameterException, InvalidAmountOfParametersException {
		x1 = 10;
		y1 = 156;

		severity1 = EmergencySeverity.NORMAL;
		description1 = "Brand";
		size1 = FireSize.LOCAL;
		chemical1 = false;
		trappedPeople1 = 0;
		numberOfInjured1 = 123654;

		time1 = new Time(0, 10);
		location1 = new Location(x1, y1);
		world = new World();
		event1 = new FireEvent(time1, world, location1, severity1, description1, size1, chemical1, trappedPeople1, numberOfInjured1);
	}

	@Test
	public void testConstructor() throws InvalidWorldException {
		emergencyDispatchApi = new EmergencyDispatchApi(world);
		assertEquals(world, emergencyDispatchApi.getWorld());
	}

	@Test
	public void testRegisterNewEvent() throws InvalidWorldException, EmergencyDispatchException{
		emergencyDispatchApi = new EmergencyDispatchApi(world);
		emergencyDispatchApi.registerNewEvent(event1);
	}
}
