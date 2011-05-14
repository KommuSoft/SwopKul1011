package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.api.EmergencyDispatchException;
import be.kuleuven.cs.swop.api.IFireView;
import be.kuleuven.cs.swop.api.IPublicDisturbanceView;
import be.kuleuven.cs.swop.api.IRobberyView;
import be.kuleuven.cs.swop.api.ITrafficAccidentView;
import java.util.logging.Level;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.lists.EmergencyFactoryList;
import projectswop20102011.domain.lists.ParserList;
import projectswop20102011.World;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.Fire;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.PublicDisturbance;
import projectswop20102011.domain.Robbery;
import projectswop20102011.domain.TrafficAccident;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;
import projectswop20102011.externalsystem.adapters.FireAdapter;
import projectswop20102011.externalsystem.adapters.PublicDisturbanceAdapter;
import projectswop20102011.externalsystem.adapters.RobberyAdapter;
import projectswop20102011.externalsystem.adapters.TimeAdapter;
import projectswop20102011.externalsystem.adapters.TrafficAccidentAdapter;
import projectswop20102011.factories.FireFactory;
import projectswop20102011.factories.PublicDisturbanceFactory;
import projectswop20102011.factories.RobberyFactory;
import projectswop20102011.factories.TrafficAccidentFactory;
import projectswop20102011.utils.parsers.BooleanParser;
import projectswop20102011.utils.parsers.EmergencySeverityParser;
import projectswop20102011.utils.parsers.FireSizeParser;
import projectswop20102011.utils.parsers.GPSCoordinateParser;
import projectswop20102011.utils.parsers.IntegerParser;
import projectswop20102011.utils.parsers.LongParser;
import projectswop20102011.utils.parsers.StringParser;

public class EmergencyDispatchApiTest {

	private World world;
	private EmergencyDispatchApi api;
	private IFireView fire1, fire2, fire3, fire4;
	private IRobberyView robbery1, robbery2, robbery3, robbery4;
	private IPublicDisturbanceView publicDisturbance1, publicDisturbance2, publicDisturbance3, publicDisturbance4;
	private ITrafficAccidentView trafficAccident1, trafficAccident2, trafficAccident3, trafficAccident4;

	@Before
	public void setUp() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
		world = new World();
		EmergencyFactoryList efl = world.getEmergencyFactoryList();
		api = new EmergencyDispatchApi(world);

		try {
			efl.addEmergencyFactory(new FireFactory());
			efl.addEmergencyFactory(new TrafficAccidentFactory());
			efl.addEmergencyFactory(new RobberyFactory());
			efl.addEmergencyFactory(new PublicDisturbanceFactory());

		} catch (InvalidEmergencyTypeNameException ex) {
			java.util.logging.Logger.getLogger(EmergencyDispatchApiTest.class.getName()).log(Level.SEVERE, null, ex);
		}

		ParserList pl = world.getParserList();
		pl.addParser(new BooleanParser());
		pl.addParser(new EmergencySeverityParser());
		pl.addParser(new FireSizeParser());
		pl.addParser(new GPSCoordinateParser());
		pl.addParser(new IntegerParser());
		pl.addParser(new LongParser());
		pl.addParser(new StringParser());


		Fire f1, f2, f3, f4;
		Robbery r1, r2, r3, r4;
		PublicDisturbance pd1, pd2, pd3, pd4;
		TrafficAccident ta1, ta2, ta3, ta4;

		f1 = new Fire(new GPSCoordinate(10, 20), EmergencySeverity.BENIGN, "brandje", FireSize.FACILITY, true, 0, 2);
		f2 = new Fire(new GPSCoordinate(1, 20), EmergencySeverity.SERIOUS, "Brandje", FireSize.HOUSE, true, 2, 2);
		f3 = new Fire(new GPSCoordinate(10, 2), EmergencySeverity.URGENT, "brandje", FireSize.LOCAL, false, 2, 0);
		f4 = new Fire(new GPSCoordinate(-10, 20), EmergencySeverity.NORMAL, "brandje", FireSize.FACILITY, true, 0, 2);

		fire1 = new FireAdapter(f1);
		fire2 = new FireAdapter(f2, new TimeAdapter(0,10));
		fire3 = new FireAdapter(f3, new TimeAdapter(2,10));
		fire4 = new FireAdapter(f4, new TimeAdapter(1,0));

		r1 = new Robbery(new GPSCoordinate(0, 10), EmergencySeverity.NORMAL, "overvalletje", true, false);
		r2 = new Robbery(new GPSCoordinate(1, 10), EmergencySeverity.SERIOUS, "overvalletje", false, false);
		r3 = new Robbery(new GPSCoordinate(3, 0), EmergencySeverity.URGENT, "overvalletje", true, true);
		r4 = new Robbery(new GPSCoordinate(-5, 10), EmergencySeverity.BENIGN, "overvalletje", false, true);

		robbery1 = new RobberyAdapter(r1);
		robbery2 = new RobberyAdapter(r2);
		robbery3 = new RobberyAdapter(r3);
		robbery4 = new RobberyAdapter(r4);

		pd1 = new PublicDisturbance(new GPSCoordinate(0, 5), EmergencySeverity.SERIOUS, "", 10);
		pd2 = new PublicDisturbance(new GPSCoordinate(0, 5), EmergencySeverity.URGENT, "", 10);
		pd3 = new PublicDisturbance(new GPSCoordinate(0, 5), EmergencySeverity.NORMAL, "", 10);
		pd4 = new PublicDisturbance(new GPSCoordinate(0, 5), EmergencySeverity.BENIGN, "", 10);

		publicDisturbance1 = new PublicDisturbanceAdapter(pd1);
		publicDisturbance2 = new PublicDisturbanceAdapter(pd2);
		publicDisturbance3 = new PublicDisturbanceAdapter(pd3);
		publicDisturbance4 = new PublicDisturbanceAdapter(pd4);

		ta1 = new TrafficAccident(new GPSCoordinate(0, -5), EmergencySeverity.BENIGN, "" , 1L, 3L);
		ta2 = new TrafficAccident(new GPSCoordinate(0, -5), EmergencySeverity.NORMAL, "" , 1L, 3L);
		ta3 = new TrafficAccident(new GPSCoordinate(0, -5), EmergencySeverity.SERIOUS, "" , 1L, 3L);
		ta4 = new TrafficAccident(new GPSCoordinate(0, -5), EmergencySeverity.URGENT, "" , 1L, 3L);

		trafficAccident1 = new TrafficAccidentAdapter(ta1);
		trafficAccident2 = new TrafficAccidentAdapter(ta2);
		trafficAccident3 = new TrafficAccidentAdapter(ta3);
		trafficAccident4 = new TrafficAccidentAdapter(ta4);
	}

	@Test
	public void testAdvanceTime() throws EmergencyDispatchException{
		assertEquals(0, world.getTime());
		api.advanceTime(new TimeAdapter(0, 1));
		assertEquals(60, world.getTime());
		api.advanceTime(new TimeAdapter(1, 0));
		assertEquals(3600+60, world.getTime());
	}

	@Test
	public void testRegisterNewEvent() throws EmergencyDispatchException {
		assertEquals(0, world.getEmergencyList().toArray().length);
		api.registerNewEvent(fire1);
		api.registerNewEvent(fire2);
		api.registerNewEvent(fire3);
		api.registerNewEvent(fire4);

		api.registerNewEvent(robbery1);
		api.registerNewEvent(robbery2);
		api.registerNewEvent(robbery3);
		api.registerNewEvent(robbery4);

		api.registerNewEvent(publicDisturbance1);
		api.registerNewEvent(publicDisturbance2);
		api.registerNewEvent(publicDisturbance3);
		api.registerNewEvent(publicDisturbance4);

		api.registerNewEvent(trafficAccident1);
		api.registerNewEvent(trafficAccident2);
		api.registerNewEvent(trafficAccident3);
		api.registerNewEvent(trafficAccident4);

		assertEquals(13, world.getEmergencyList().toArray().length);
		api.advanceTime(new TimeAdapter(0,10));
		assertEquals(14, world.getEmergencyList().toArray().length);
		api.advanceTime(new TimeAdapter(3,0));
		assertEquals(16, world.getEmergencyList().toArray().length);
	}

	@Test
	public void testGetListOfEmergencies(){
		
	}
}
