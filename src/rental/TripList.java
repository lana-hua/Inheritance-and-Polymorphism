package rental;

import util.List;

/**
 * TripList class represents the circular linked list.
 * This class contains the reference to the last node in the linked list.
 * @author Lana Huang, Sharon Chen
 */
public class TripList extends List<Node> {
    private Node last;

    public Node getLast() {
        return last;
    }

    /**
     * Add given New Node to circular linked list.
     * @param newNode The new node to be added to linked list.
     */
    @Override
    public void add(Node newNode) {
        if (last == null) {
            newNode.next = newNode;
            last = newNode;
        } else {
            newNode.next = last.next;
            last.next = newNode;
            last = newNode;
        }
    }

    /**
     * Checks if the list is empty.
     */
    @Override
    public boolean isEmpty() {
        return last == null;
    }


}
