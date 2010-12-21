package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.events.SimpleScenario;
import be.kuleuven.cs.swop.external.ExternalSystemException;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidWorldException;

public class NewMain {
    public static void main(String[] args) throws ExternalSystemException, InvalidWorldException {
		World world = new World();
		EmergencyDispatchApi api = new EmergencyDispatchApi(world);
		SimpleScenario ss = new SimpleScenario(api);
		Time t = new Time(1,10);

		System.out.println("Vooraf begin");
		for(Emergency tettn : world.getEmergencyList()){
			System.out.println(tettn.getLongInformation());
		}
		System.out.println("Vooraf einde");
		ss.notifyTimeChanged(t);
		System.out.println("naaf begin");
		for(Emergency tettn : world.getEmergencyList()){
			System.out.println(tettn);
		}
		System.out.println("naaf einde");
    }

}