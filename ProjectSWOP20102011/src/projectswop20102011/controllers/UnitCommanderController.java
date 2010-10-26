package projectswop20102011.controllers;

/**
 * A class that handles all commands of the Unit Commander user.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class UnitCommanderController implements Controller {

    private final MainController mainController;
    
    public UnitCommanderController (MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void readInput(String expression) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}