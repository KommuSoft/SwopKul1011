package projectswop20102011.controllers;

import projectswop20102011.EmergencyList;
import projectswop20102011.exceptions.InvalidCommandException;

/**
 * A controller that redirects expressions expressed by the dispatcher actor
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
class DispatcherController implements Controller {

    private final EmergencyList emergencyList;

    public DispatcherController (EmergencyList emergencyList) {
        this.emergencyList = emergencyList;
    }

    /**
     * Reads the input 
     * @param expression
     * @throws InvalidCommandException InvalidCommandException if a command is expressed that is unknown by the OperatorControl.
     */
    @Override
    public void readInput(String expression) throws InvalidCommandException {
        throw new InvalidCommandException(String.format("Dispatcher controller doesn't know a command \"%s\"", expression));
    }

}
