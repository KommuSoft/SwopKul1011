package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.events.SimpleScenario;
import be.kuleuven.cs.swop.external.ExternalSystemException;
import projectswop20102011.domain.World;

public class NewMain {
    public static void main(String[] args) throws ExternalSystemException {
		World world = new World();
		EmergencyDispatchApi api = new EmergencyDispatchApi(world);
		SimpleScenario ss = new SimpleScenario(api);
		Time t = new Time(10,10);
		ss.notifyTimeChanged(t);
    }

}