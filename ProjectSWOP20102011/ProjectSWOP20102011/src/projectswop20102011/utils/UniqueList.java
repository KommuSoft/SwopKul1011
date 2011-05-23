package projectswop20102011.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/**
 * A list only containing unique elements, but maintaining the order of addition.
 * @param <T> The type of objects the UniqueList will contain.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class UniqueList<T> implements List<T>, Set<T> {

    /**
     * A hashSet to provide the uniqueness.
     */
    private final HashSet<T> hashSet = new HashSet<T>();
    /**
     * An ArrayList to provide the order of addition.
     */
    private final ArrayList<T> arrayList = new ArrayList<T>();

    /**
     * Returns the HashSet of this structure.
     * @return the HashSet of this structure.
     */
    private HashSet<T> takeHashSet() {
        return this.hashSet;
    }

    /**
     * Returns the ArrayList of this structure.
     * @return the ArrayList of this structure.
     */
    private ArrayList<T> takeArrayList() {
        return this.arrayList;
    }

    /**
     * Returns the number of elements contained by the UniqueList.
     * @return the number of elements contained by the UniqueList.
     */
    @Override
    public int size() {
        return this.takeHashSet().size();
    }

    /**
     * Checks if the UniqueList is empty.
     * @return True if the UniqueList is empty, otherwise false.
     */
    @Override
    public boolean isEmpty() {
        return this.takeHashSet().isEmpty();
    }

    /**
     * Check if the UniqueList contains the given object.
     * @param o
     *          The given object to check for.
     * @return True if the given object is in the UniqueList, false otherwise.
     */
    @Override
    public boolean contains(Object o) {
        return this.takeHashSet().contains(o);
    }

    /**
     * Provides an interator to iterate over the UniqueList maintaining the order of addition.
     * @return An iterator to iterate over the UniqueList.
     */
    @Override
    public Iterator<T> iterator() {
        return this.takeArrayList().iterator();
    }

    /**
     * Generates an array out of the UniqueList maintaining the order of addition.
     * @return An array out of the UniqueList maintaining the order.
     */
    @Override
    public Object[] toArray() {
        return this.takeArrayList().toArray();
    }

    /**
     * Generates a generic array out of the UniqueList maintaining the order of addition and having the same type as the given array.
     * @param <T> The type of array to return.
     * @param ts An array that provides the type of the resulting array.
     * @return A generic array maintaining the order of addition.
     */
    @Override
    public <T> T[] toArray(T[] ts) {
        return this.takeArrayList().toArray(ts);
    }

    /**
     * Adds an element to the UniqueList.
     * @param e
     *          The element to add.
     * @return True if the UniqueList has changed, false otherwise.
     */
    @Override
    public boolean add(T e) {
        return (this.takeHashSet().add(e) && this.takeArrayList().add(e));
    }

    /**
     * Removes the given object from the UniqueList.
     * @param o
     *          The object to remove.
     * @return True if this operation has modified the UniqueList.
     */
    @Override
    public boolean remove(Object o) {
        return (this.takeHashSet().remove(o) && this.takeArrayList().remove(o));
    }

    /**
     * Checks if the UniqueList contains all the elements in the given collection.
     * @param clctn
     *          The given collection.
     * @return True if the UniqueList contains all the elements, otherwise false.
     */
    @Override
    public boolean containsAll(Collection<?> clctn) {
        return this.takeHashSet().containsAll(clctn);
    }

    /**
     * Adds all the elements in the given collection to the UniqueList.
     * @param clctn
     *          The given collection of elements to all be added to the UniqueList.
     * @return True if the UniqueList has been changed.
     */
    @Override
    public boolean addAll(Collection<? extends T> clctn) {
        boolean changed = false;
        for(T t : clctn) {
            changed |= this.add(t);
        }
        return changed;
    }

    /**
     * Insert all the elements of the specified collection intro the UniqueList at the given index.
     * @param i
     *          The given index to start insertion.
     * @param clctn
     *          The collection of objects that all must be added to the UniqueList.
     * @return True if the UniqueList has changed, false otherwise.
     */
    @Override
    public boolean addAll(int i, Collection<? extends T> clctn) {
        int index = i;
        boolean changed = false;
        for(T e : clctn) {
            if(this.takeHashSet().add(e)) {
                this.takeArrayList().add(index,e);
                index++;
                changed = true;
            }
        }
        return changed;
    }

    /**
     * Removes all the elements from the UniqueList who are in the given collection.
     * @param clctn
     *          The given collection.
     * @return True if the UniqueList has changed, false otherwise.
     */
    @Override
    public boolean removeAll(Collection<?> clctn) {
        return (this.takeHashSet().removeAll(clctn) && this.takeArrayList().removeAll(clctn));
    }

    /**
     * Removes all the elements who are not contained by the given collection.
     * @param clctn
     *          The given collection.
     * @return True if the UniqueList has changed, false otherwise.
     */
    @Override
    public boolean retainAll(Collection<?> clctn) {
        return (this.takeHashSet().retainAll(clctn) && this.takeArrayList().retainAll(clctn));
    }

    /**
     * Clears the UniqueList.
     */
    @Override
    public void clear() {
        this.takeHashSet().clear();
        this.takeArrayList().clear();
    }

    /**
     * Gets the element on the i-th position.
     * @param i
     *          The index of the object to get from the UniqueList.
     * @return The element at index i.
     */
    @Override
    public T get(int i) {
        return this.takeArrayList().get(i);
    }

    /**
     * Replaces the element at a specified position in the UniqueList with a given element.
     * @param i
     *          The position where the element must be replaced.
     * @param e
     *          The element to replace.
     * @return The previous element at the location, or Null if the object could not be added (because it is already in the collection).
     */
    @Override
    public T set(int i, T e) {
        if(this.takeHashSet().add(e)) {
            T old = this.takeArrayList().set(i,e);
            this.takeHashSet().remove(old);
            return old;
        }
        else {
            return null;
        }
    }

    /**
     * Inserts an element in the UniqueList at a given position.
     * @param i
     *          The index where to insert the element.
     * @param e
     *          The element to insert.
     * @throws IllegalArgumentException
     *          If the UniqueList already contains the given element.
     */
    @Override
    public void add(int i, T e) {
        if(this.takeHashSet().add(e)) {
            this.takeArrayList().add(i,e);
        }
        else {
            throw new IllegalArgumentException("UniqueList contains already the element.");
        }
    }

    /**
     * Removes the i-th element from the UniqueList.
     * @param i
     *          The position where to remove the element.
     * @return The element previous at the specified position.
     */
    @Override
    public T remove(int i) {
        T old = this.get(i);
        this.takeHashSet().remove(old);
        return this.takeArrayList().remove(i);
    }

    /**
     * Returns the position of the given element in the UniqueList.
     * @param o
     *          The given object to search the position from.
     * @return The position in the UniqueList of the given element.
     */
    @Override
    public int indexOf(Object o) {
        return this.takeArrayList().indexOf(o);
    }

    /**
     * A method that does exactly the same as indexOf
     * @param o
     *          The given object to search the position from.
     * @return The position in the UniqueList of the given element.
     */
    @Override
    public int lastIndexOf(Object o) {
        return indexOf(o);
    }

    /**
     * Returns a list iterator of the elements in the UniqueList maintaining the order.
     * @return A list iterator of the elements in the UniqueList maintaining the order.
     */
    @Override
    public ListIterator<T> listIterator() {
        return this.takeArrayList().listIterator();
    }

    /**
     * Returns a list iterator of the elements in the UniqueList maintaining the order starting from the given position.
     * @param i
     *          The index where to start from.
     * @return A list iterator of the elements in the UniqueList maintaining the order starting from the given position.
     */
    @Override
    public ListIterator<T> listIterator(int i) {
        return this.takeArrayList().listIterator(i);
    }


    /**
     * Returns a sub list from the given fromIndex to the toIndex.
     * @param fromIndex
     *          The index where to start the sub list.
     * @param toIndex
     *          The index where to stop the sub list.
     * @return A sub list from the given fromIndex to the toIndex.
     */
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return this.takeArrayList().subList(fromIndex, toIndex);
    }
}