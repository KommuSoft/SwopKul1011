package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.events.SimpleScenario;
import be.kuleuven.cs.swop.external.ExternalSystemException;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.domain.EmergencyStatus;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidWorldException;

public class NewMain {
    public static void main(String[] args) throws ExternalSystemException, InvalidWorldException {
		World world = new World();
		EmergencyDispatchApi api = new EmergencyDispatchApi(world);
		SimpleScenario ss = new SimpleScenario(api);
		Time t = new Time(10,10);

		InspectEmergenciesController iec = new InspectEmergenciesController(world);
		iec.inspectEmergenciesOnStatus(EmergencyStatus.COMPLETED);
		ss.notifyTimeChanged(t);

    }

}