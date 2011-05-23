package projectswop20102011.utils;

import java.util.Comparator;

/**
 * A default comparator class where all objects are equal.
 * @param <T> The type of objects to compare.
 * @author Willem Van Onsem, Jnas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DefaultComparator<T> implements Comparator<T> {

    /**
     * The compare method that compares the given object.
     * @param o1
     *          The first object.
     * @param o2
     *          The second object.
     * @return Always zero.
     */
    @Override
    public int compare(T o1, T o2) {
        return 0;
    }

}
