package projectswop20102011.eventhandlers;

import java.util.ArrayList;

/**
 * A class that will be used to trigger some actions when a certain event happens.
 * @param <T> The type of the argument EventHandlers that will be triggered.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Event<T> {

    /**
     * An arraylist containing the several handlers that will be triggered.
     */
    private final ArrayList<EventHandler<T>> registeredHandlers = new ArrayList<EventHandler<T>>();

    /**
     * Returns a copy of the list with registered EventHandlers.
     * @return A copy of the list with registered EventHandlers.
     */
    public ArrayList<EventHandler<T>> getRegisteredHandlers () {
        return (ArrayList<EventHandler<T>>) this.takeRegisteredHandlers().clone();
    }
    /**
     * Returns the original list with registered EventHandlers.
     * @return the original list with registered EventHandlers.
     */
    protected ArrayList<EventHandler<T>> takeRegisteredHandlers () {
        return this.registeredHandlers;
    }
    /**
     * Register a new EventHandler to the Event.
     * @param handler The handler to register.
     */
    public void registerHandler (EventHandler<T> handler) {
        this.takeRegisteredHandlers().add(handler);
    }
    /**
     * Remove an EventHandler from the list of registered EventHandlers.
     * @param handler The handler to remove.
     */
    public void unregisterHandler (EventHandler<T> handler) {
        this.takeRegisteredHandlers().remove(handler);
    }
    /**
     * A method called when the event is happening, this will trigger all the registered EventHandlers.
     * @param argument The argument that specifies information about the event.
     */
    public void action (T argument) {
        for(EventHandler<T> handler : this.takeRegisteredHandlers()) {
            handler.handleEvent(argument);
        }
    }

}
