package projectswop20102011.eventhandlers;

import java.util.ArrayList;

/**
 * A class that will be used to trigger some actions when a certain event happens.
 * @param <T> The type of EventHandlers that will be triggered.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Event<T extends EventHandler> {

    private ArrayList<T> registeredHandlers;

    public ArrayList<T> getRegisteredHandlers () {
        return (ArrayList<T>) this.takeRegisteredHandlers().clone();
    }
    protected ArrayList<T> takeRegisteredHandlers () {
        return this.registeredHandlers;
    }
    public void registerHandler (T handler) {
        this.takeRegisteredHandlers().add(handler);
    }
    public void unregisterHandler (T handler) {
        this.takeRegisteredHandlers().remove(handler);
    }
    public void action (Object... arguments) {

    }

}
