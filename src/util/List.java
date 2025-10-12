package util;

import vehicle.Vehicle;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @param <E>
 */
public class List<E> implements Iterable<E> {
    private E[] objects; //E is the name for the generic type
    private int size;

    public List() {
        Object[] temp = new Object[4];
        objects = (E[]) temp;
        size = 0;
    }

    /**
     * Finds the index of the given object in the fleet.
     * If it does not exist in the List it returns -1, NOT_FOUND.
     * @param e The object to be found in the fleet.
     * @return index if found; -1, NOT_FOUND otherwise.
     */
    private int find(E e) {
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(e)) {
                return i;
            }
        }
        return -1;
    } //return -1 if not found

    /**
     * Grows the array of the List by 4 if the List reaches capacity
     */
    private void grow() {
        Object[] temp = new Object[size + 4]; // create a new larger array
        E[] newArray = (E[]) temp;

        // manually copy old elements into the new array
        for (int i = 0; i < size; i++) {
            newArray[i] = objects[i];
        }

        // assign new array to objects
        objects = newArray;
    } //grow the size of the array by 4

    /**
     * Checks if the List contains an object given the object.
     * @param e The object being checked against the List.
     * @return true if the object is found; false otherwise.
     */
    public boolean contains(E e) {
        if (find(e) != -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Add given object to the List.
     * If the List is already at capacity, call grow to increase capacity.
     * @param e object to be added to the List.
     */
    public void add(E e) {
        if (!contains(e)) {
            if (size == objects.length) {
                grow();
            }
            objects[size] = e;
            size++;
        }
    }

    /**
     * Remove the given object from the List
     * It does nothing if object is not in List. It overwrites with the last object in the List
     * @param e the object to be removed from the List
     */
    public void remove(E e) {
        int index = find(e);
        if (index != -1) {
            objects[index] = objects[size - 1];
            objects[size - 1] = null;
            size--;
        }
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the size of the object list.
     * @return the number of objects in the list.
     */
    public int size() {
        return size;
    }

    public Iterator<E> iterator() {
        return new ListIterator<>();
    } //traversing the list using for each

    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return objects[index];
    } //return the object at the index

    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        objects[index] = e;
    } //put object e at the index

    public int indexOf(E e) {
        return find(e);
    } //return index of object e, or return -1

    //private inner class for the iterator to work properly
    private class ListIterator<E> implements Iterator<E> {
        int current = 0; //current index when traversing the list (array)
        public boolean hasNext(){
            if (isEmpty()) {
                return false;
            } else {
                return current < size;
            }
        } //if it’s empty or at the end of the array
        public E next() {
            if (hasNext()) {
                return (E) objects[current++];
            } else {
                throw new NoSuchElementException();
            }
        } //return the next object in the list
    }
}
