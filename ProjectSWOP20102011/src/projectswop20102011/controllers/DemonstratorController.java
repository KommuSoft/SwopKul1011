package projectswop20102011.controllers;

/**
 * A controller class that handles all commands from the demonstrator user.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DemonstratorController implements Controller {

    private final MainController mainController;
    
    public DemonstratorController (MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void readInput(String expression) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
