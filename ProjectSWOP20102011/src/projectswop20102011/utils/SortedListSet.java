package projectswop20102011.utils;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.SortedSet;

/**
 * A datastructure that maintains the order in a set.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class SortedListSet<T> implements SortedSet<T> {

    private final PriorityQueue<T> queue;

    public SortedListSet(Comparator<? super T> comparator) {
        this.queue = new PriorityQueue<T>(11, comparator);
    }

    @Override
    public Comparator<? super T> comparator() {
        return this.queue.comparator();
    }

    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        Comparator<? super T> comparator = this.comparator();
        SortedListSet<T> subSet = new SortedListSet<T>(this.comparator());
        for (T t : this.queue) {
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
        for (T t : this.queue) {
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
        for (T t : this.queue) {
            if (comparator.compare(fromElement, t) >= 0) {
                tailSet.add(t);
            }
        }
        return tailSet;
    }

    @Override
    public T first() {
        return this.queue.peek();
    }

    @Override
    public T last() {
        T last = null;
        for (T t : this.queue) {
            last = t;
        }
        return last;
    }

    @Override
    public int size() {
        return this.queue.size();
    }

    @Override
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.queue.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return this.queue.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.queue.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.queue.toArray(a);
    }

    @Override
    public boolean add(T e) {
        if (!this.contains(e)) {
            return this.queue.add(e);
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        return this.queue.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.queue.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean added = false;
        for(T t : c) {
            added |= this.add(t);
        }
        return added;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.queue.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.queue.removeAll(c);
    }

    @Override
    public void clear() {
        this.queue.clear();
    }
    
}