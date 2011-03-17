package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.events.SimpleScenario;
import be.kuleuven.cs.swop.events.Time;
import be.kuleuven.cs.swop.external.ExternalSystemException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.lists.EmergencyFactoryList;
import projectswop20102011.domain.lists.ParserList;
import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
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
    private Time time;
    private SimpleScenario simpleScenario;

    @Before
    public void setUp() {
        try {
            world = new World();
            EmergencyFactoryList efl = world.getEmergencyFactoryList();
            efl.addEmergencyFactory(new FireFactory());
            efl.addEmergencyFactory(new TrafficAccidentFactory());
            efl.addEmergencyFactory(new RobberyFactory());
            efl.addEmergencyFactory(new PublicDisturbanceFactory());
            ParserList pl = world.getParserList();
            pl.addParser(new BooleanParser());
            pl.addParser(new EmergencySeverityParser());
            pl.addParser(new FireSizeParser());
            pl.addParser(new GPSCoordinateParser());
            pl.addParser(new IntegerParser());
            pl.addParser(new LongParser());
            pl.addParser(new StringParser());
            time = new Time(1, 10);
            api = new EmergencyDispatchApi(world);
            simpleScenario = new SimpleScenario(api);
        } catch (InvalidEmergencyTypeNameException ex) {
            Logger.getLogger(EmergencyDispatchApiTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testNotifyTimeChanged() throws ExternalSystemException {
        assertEquals(0, world.getEmergencyList().toArray().length);
        simpleScenario.notifyTimeChanged(time);
        assertEquals(2, world.getEmergencyList().toArray().length);
    }
}
