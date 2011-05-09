package projectswop20102011.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * An OrderedSet<T> implementation with a LinkedList to maintain the order and a HashSet to check if the structure already contains the object.
 * @param <T> The type of objects this structure will contain.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class LinkedListHashOrderedSet<T> implements OrderedSet<T> {

    private final HashSet<T> hashset = new HashSet<T>();
    private final LinkedList<T> list = new LinkedList<T>();

    @Override
    public int size() {
        return this.hashset.size();
    }

    @Override
    public boolean isEmpty() {
        return this.hashset.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.hashset.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return this.list.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.list.toArray(a);
    }

    @Override
    public boolean add(T e) {
        if(!this.hashset.contains(e)) {
            this.hashset.add(e);
            this.list.add(e);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if(this.hashset.contains(o)) {
            this.hashset.remove(o);
            this.list.remove(o);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.hashset.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean addedAll = false;
        for(T t : c) {
            addedAll |= this.add(t);
        }
        return addedAll;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for(T t : this) {
            if(!c.contains(t)) {
                changed |= this.remove(t);
            }
        }
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean removedAll = false;
        for(Object o : c) {
            removedAll |= this.remove(o);
        }
        return removedAll;
    }

    @Override
    public void clear() {
        this.list.clear();
        this.hashset.clear();
    }

}
