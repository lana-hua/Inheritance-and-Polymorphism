package rental;

/**
 * TripList class represents the circular linked list.
 * This class contains the reference to the last node in the linked list.
 * @author Lana Huang
 */
public class TripList {
    private Node last;

    /**
     * Add New Node with given trip to circular linked list
     * @param trip The trip stored in new node to be added to linked list
     */
    public void add(Trip trip) {
        Node newNode = new Node(trip);
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
     * Print all completed trips in the circular linked list, tripList, ordered by end date.
     * This method creates a visited boolean array that keeps track of Nodes already visited.
     * It then repeatedly finds the unvisited node with the earliest date, prints the trip information.
     * Then it marks the node as visited and continues this process until all are printed.
     */
    public void print() {
        if (last == null) {
            System.out.println("There is no archived trips.");
            return;
        }
        System.out.println("*List of completed trips ordered by ending date.");

        int length = 1;
        Node ptr = last.next;

        while (ptr != last) {
            length++;
            ptr = ptr.next;
        }

        boolean[] visited = new boolean[length];

        for (int i = 0; i < length; i++) {
            Node minNode = null;
            int minIndex = -1;

            ptr = last.next;
            for (int j = 0; j < length; j++) {
                if (!visited[j]) {
                    if (minNode == null || (ptr.trip.getBooking().getEnd().compareTo(minNode.trip.getBooking().getEnd()) < 0)) {
                        minNode = ptr;
                        minIndex = j;
                    }
                }
                ptr = ptr.next;
            }
            System.out.println(minNode.trip.toString());
            visited[minIndex] = true;

        }
        System.out.println("*end of list.\n");
    }
}
