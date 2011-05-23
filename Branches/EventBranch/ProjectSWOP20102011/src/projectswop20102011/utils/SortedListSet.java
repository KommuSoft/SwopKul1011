package projectswop20102011.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.SortedSet;

/**
 * A datastructure that maintains the order in a set.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class SortedListSet<T> implements SortedSet<T> {

    private final ArrayList<T> list;
    private Comparator<? super T> comparator;
    private boolean sorted = true;

    public SortedListSet() {
        this.list = new ArrayList<T>();
        this.comparator = new DefaultComparator<T>();
    }

    public SortedListSet(Comparator<? super T> comparator) {
        this();
        this.comparator = comparator;
    }

    public SortedListSet(Collection<? extends T> items) {
        this();
        this.addAll(items);
    }

    public SortedListSet(Comparator<? super T> comparator, Collection<? extends T> items) {
        this(comparator);
        this.addAll(items);
    }

    private synchronized void ensureSorted() {
        if (!sorted) {
            sorted = true;
            Collections.sort(this.list, this.comparator);
        }
    }

    @Override
    public Comparator<? super T> comparator() {
        return this.comparator();
    }

    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        Comparator<? super T> comparator = this.comparator();
        SortedListSet<T> subSet = new SortedListSet<T>(this.comparator());
        for (T t : this) {
            if (comparator.compare(t, toElement) > 0) {
                break;
            } else if (comparator.compare(fromElement, t) >= 0) {
                subSet.add(t);
            }
        }
        return subSet;
    }

    @Override
    public SortedSet<T> headSet(T toElement) {
        Comparator<? super T> comparator = this.comparator();
        SortedListSet<T> headSet = new SortedListSet<T>(this.comparator());
        for (T t : this) {
            if (comparator.compare(t, toElement) > 0) {
                break;
            }
            headSet.add(t);
        }
        return headSet;
    }

    @Override
    public SortedSet<T> tailSet(T fromElement) {
        Comparator<? super T> comparator = this.comparator();
        SortedListSet<T> tailSet = new SortedListSet<T>(this.comparator());
        for (T t : this) {
            if (comparator.compare(fromElement, t) >= 0) {
                tailSet.add(t);
            }
        }
        return tailSet;
    }

    @Override
    public T first() {
        this.ensureSorted();
        if (this.list.size() > 0) {
            return this.list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public T last() {
        this.ensureSorted();
        if (this.list.size() > 0) {
            return this.list.get(this.size()-1);
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.list.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        this.ensureSorted();
        return this.list.iterator();
    }

    @Override
    public Object[] toArray() {
        this.ensureSorted();
        return this.list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        this.ensureSorted();
        return this.list.toArray(a);
    }

    @Override
    public boolean add(T e) {
        if (!this.contains(e)) {
            this.sorted = false;
            return this.list.add(e);
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        return this.list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean added = false;
        for (T item : c) {
            added |= this.add(item);
        }
        return added;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.list.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.list.removeAll(c);
    }

    @Override
    public void clear() {
        this.list.clear();
    }
}