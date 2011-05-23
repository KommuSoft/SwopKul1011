package projectswop20102011.utils;

/**
 * An object that is used to hold an object for passing output parameters.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class ObjectHolder<T> {

    private T object;

    /**
     * Returns the object holded by this ObjectHolder.
     * @return the object holded by this ObjectHolder.
     */
    public T getObject () {
        return this.object;
    }
    /**
     * Sets the object of the holder to the given object.
     * @param object the new object of this ObjectHolder.
     * @post the object of the holder is equal to the given object
     *          | object == this.getObject()
     */
    public void setObject (T object) {
        this.object = object;
    }

}
