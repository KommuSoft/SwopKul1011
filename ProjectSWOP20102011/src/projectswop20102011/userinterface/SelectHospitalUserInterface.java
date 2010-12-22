package projectswop20102011.userinterface;

import java.util.ArrayList;
import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.Hospital;
import projectswop20102011.controllers.SelectHospitalController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.ParsingAbortedException;
import projectswop20102011.userinterface.parsers.IntegerParser;
import projectswop20102011.userinterface.parsers.StringParser;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class SelectHospitalUserInterface extends CommandUserInterface {

    private final SelectHospitalController controller;

    public SelectHospitalUserInterface(SelectHospitalController controller) throws InvalidCommandNameException, InvalidControllerException {
        super("select hospital");
        if (controller == null) {
            throw new InvalidControllerException("Controller must be effective.");
        }
        this.controller = controller;
    }

    @Override
    public SelectHospitalController getController() {
        return this.controller;
    }

    @Override
    public void HandleUserInterface() {
        try {
            String name = this.parseInputToType(new StringParser(), "name of the unit");
            Ambulance amb = this.getController().findAmbulance(name);
            if (amb != null) {
                this.writeOutput(String.format("Login %s", amb));
                if (!amb.isAssigned()) {
                    this.writeOutput("Ambulance is not assigned to an emergency");
                } else if (!amb.isAtDestination()) {
                    this.writeOutput("Ambulance is not at the location of the emergency");
                } else {
                    ArrayList<Hospital> hospitals;
                    try {
                        hospitals = this.getController().getHospitalList(amb);
                        if (hospitals.size() > 0) {
                            for(int i = 0; i < hospitals.size(); i++) {
                                Hospital h = hospitals.get(i);
                                double distance = h.getHomeLocation().getDistanceTo(amb.getEmergency().getLocation());
                                this.writeOutput(String.format("%s\t|\t%s\t|\t%s",i,h.getName(),distance));
                            }
                            int selected = this.parseInputToType(new IntegerParser(), "selected hospital id");
                            if (selected < 0 || selected >= hospitals.size()) {
                                this.writeOutput("Couldn't find hospital id.");
                            } else {
                                try {
                                    this.getController().SelectHospital(amb, hospitals.get(selected));
                                    this.writeOutput("Select Hosital Done!");
                                } catch (Exception ex) {
                                    this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
                                }
                            }
                        } else {
                            this.writeOutput("No hospitals found!");
                        }
                    } catch (Exception ex) {
                        this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
                    }
                }
                this.writeOutput(String.format("Logout %s", amb));
            } else {
                this.writeOutput(String.format("No ambulance exists with name \"%s\"", name));
            }
        } catch (ParsingAbortedException ex) {
            this.writeOutput("Command aborted.");
        }
    }
}
