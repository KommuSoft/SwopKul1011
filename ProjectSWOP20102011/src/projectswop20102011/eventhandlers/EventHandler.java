package projectswop20102011.eventhandlers;

/**
 * An interface providing a method with one argument to handl a certain event.
 * @param <T> The type of argument in the handleEvent method.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 * @note If more arguments are required the user can implement a structure that contains all those arguments.
 * @see #handleEvent(java.lang.Object) 
 */
public interface EventHandler<T> {

    /**
     * A method to handl the event, with one argument.
     * @param argument The argument of the handler.
     * @note If more arguments are required the user can implement a structure that contains all those arguments.
     */
    public void handleEvent (T argument);
    
}
