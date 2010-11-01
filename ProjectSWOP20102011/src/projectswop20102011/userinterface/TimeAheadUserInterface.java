package projectswop20102011.userinterface;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.ParsingAbortedException;

/**
 * A user interface to handle the time ahead use case.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class TimeAheadUserInterface extends CommandUserInterface {

    @Override
    public String getCommandName() {
        return "time ahead";
    }

    @Override
    public void HandleUserInterface() {
        try {
            UserInterfaceParsers.parseLong(this, "time expired in seconds");
        } catch (ParsingAbortedException ex) {
            this.writeOutput("command aborted.");
        }
    }



}
