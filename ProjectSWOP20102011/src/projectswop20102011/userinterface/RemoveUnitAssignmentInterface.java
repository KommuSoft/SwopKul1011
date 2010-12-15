package projectswop20102011.userinterface;

import java.util.ArrayList;
import projectswop20102011.controllers.RemoveUnitAssignmentController;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidWithdrawalException;
import projectswop20102011.exceptions.ParsingAbortedException;
import projectswop20102011.userinterface.parsers.LongParser;
import projectswop20102011.userinterface.parsers.StringParser;

/**
 * A user interface that handles the remove unit assignment use case
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class RemoveUnitAssignmentInterface extends CommandUserInterface {

    private final RemoveUnitAssignmentController controller;

    /**
     * 
     * @param controller
     * @throws InvalidCommandNameException
     * @throws InvalidControllerException
     */
    public RemoveUnitAssignmentInterface(RemoveUnitAssignmentController controller) throws InvalidCommandNameException, InvalidControllerException {
        super("remove unit assignment");
        if (controller == null) {
            throw new InvalidControllerException("Controller must be effective.");
        }
        this.controller = controller;
    }

    @Override
    public RemoveUnitAssignmentController getController() {
        return this.controller;
    }

    @Override
    public void HandleUserInterface() {
        try {
            long emergencyId = this.parseInputToType(new LongParser(), "The id of the emergency");
            Emergency selectedEmergency = this.getController().getEmergencyFromId(emergencyId);
            if (selectedEmergency == null) {
                this.writeOutput("Emergency not found.");
            } else {
                //TODO: Hier komt dan het echte werk
                this.writeOutput("WORKING UNITS:");
                //TODO: Komt niet goed overeen met de lijst van units die eronder staan:  this.writeOutput(String.format("\t%s\t%s\t%s", "id", "Unit type", "Unit name"));
                ArrayList<Unit> workingUnits = this.getController().getWorkingUnits(selectedEmergency);
                for(int i=0;i<workingUnits.size();i++){
                    this.writeOutput(String.format("\t%s\t%s\t%s", i, workingUnits.get(i).getClass().getSimpleName(), workingUnits.get(i).getName()));
                }

                String expression;
                ArrayList<Unit> unitsToRemove = new ArrayList<Unit>(0);
                do {
                    this.writeOutput("Type in a unit id, or type \"stop\" to finish the list.");
                    expression = this.parseInputToType(new StringParser(), "id");
                    if (!expression.equals("stop")) {
                        try {
                            int id = Integer.parseInt(expression);
                            unitsToRemove.add(workingUnits.get(id));
                            this.writeOutput("Unit added.");
                        } catch (Exception ex) {
                            this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
                        }
                    }
                } while (!expression.equals("stop"));

                String[] names = new String[unitsToRemove.size()];
                for(int i=0;i<unitsToRemove.size();i++){
                    names[i] = unitsToRemove.get(i).getName();
                }
                try {
                    getController().withdrawUnits(names);
                    this.writeOutput("Units are removed");
                } catch (InvalidWithdrawalException ex) {
                    this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
                } catch (InvalidEmergencyException ex) {
                    this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
                }
            }
        } catch (ParsingAbortedException ex) {
            this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
        }
    }
}
