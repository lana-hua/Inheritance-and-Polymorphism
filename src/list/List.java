//package list;
//
//public class List<E> implements Iterable<E> {
//    private E[] objects; //E is the name for the generic type
//    private int size;
//    public List() { } //new an array type-casted to E with a capacity of 4.
//    private int find(E e) {
//
//    } //return -1 if not found
//    private void grow() {} //grow the size of the array by 4
//    public boolean contains(E e) {
//
//    }
//    public void add(E e) {}
//    public void remove(E e) {}
//    public boolean isEmpty() {
//
//    }
//    public int size() {
//
//    }
//    public Iterator<E> iterator() {
//
//    } //traversing the list using for each
//    public E get(int index) {
//
//    } //return the object at the index
//    public void set(int index, E e) {} //put object e at the index
//    public int indexOf(E e) {
//
//    } //return index of object e, or return -1
//    //private inner class for the iterator to work properly
//    private class ListIterator<E> implements Iterator<E> {
//        int current = 0; //current index when traversing the list (array)
//        public boolean hasNext(){
//
//        } //if it’s empty or at the end of the array
//        public E next(){
//
//        } //return the next object in the list
//    }
//}
