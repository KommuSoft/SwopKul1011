package projectswop20102011.userinterface;

import projectswop20102011.Ambulance;
import projectswop20102011.Hospital;
import projectswop20102011.controllers.SelectHospitalController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.ParsingAbortedException;
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
                } else if (amb.isAtDestination()) {
                    this.writeOutput("Ambulance is not at the location of the emergency");
                } else {
                    Hospital[] hospitals = this.getController().getHospitalList(amb);
                    if (hospitals.length > 0) {
                        int nameTextLengthMax = 4;
                        int locationTextLengthMax = 8;
                        int idTextLengthMax = Math.max(2,(int) Math.floor(Math.log10(hospitals.length)) + 1);
                        for (Hospital h : hospitals) {
                            nameTextLengthMax = Math.max(nameTextLengthMax, h.getName().length());
                            locationTextLengthMax = Math.max(locationTextLengthMax, h.getHomeLocation().toString().length());
                        }
                        int index = 1;
                        String idLine = new String(new char[] {'-'},0,idTextLengthMax);
                        String nameLine = new String(new char[] {'-'},0,nameTextLengthMax);
                        String locationLine = new String(new char[] {'-'},0,locationTextLengthMax);
                        this.writeOutput(String.format("\t+%s+%s+%s+",idLine,nameLine,locationLine));
                        for(Hospital h : hospitals) {
                            this.writeOutput(name);
                        }
                    } else {
                        this.writeOutput("No hospitals found!");
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
