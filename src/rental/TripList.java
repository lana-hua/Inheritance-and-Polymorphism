package rental;

/**
 * TripList class represents the circular linked list.
 * This class contains the reference to the last node in the linked list.
 * @author Lana Huang, Sharon Chen
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
            System.out.println("There is no completed trips.");
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

    /**
     * Swaps the order of trip i with trip j
     * @param i the index of one of the trip being swapped
     * @param j the index of the other trip being swapped
     */
    private void swapTrips(Trip[] trips, int i, int j) {
        Trip temp = trips[i];
        trips[i] = trips[j];
        trips[j] = temp;
    }

    private void orderTripsByDept(Trip[] trips, int length) {
        for (int i = 0; i < (length - 1); i++) {
            for (int j = 0; j < (length - i - 1); j++) {
                String dept1 = trips[j].getBooking().getEmployee().getDepartment().toString();
                String dept2 = trips[j + 1].getBooking().getEmployee().getDepartment().toString();
                //sort departments
                if (dept1.compareTo(dept2) > 0) {
                    swapTrips(trips, j, (j + 1));
                }
            }
        }
    }

    /**
     * PC Command: Prints the cost report, ordered by department, including the charge and surcharge for each trip, and the department total for all charges.
     */
    public void printCost(){
        if (last == null) {
            System.out.println("There is no archived trips for the cost report.");
            return;
        }

        int length = 1;
        Node ptr = last.next;
        while (ptr != last) {
            length++;
            ptr = ptr.next;
        }

        Trip[] trips = new Trip[length];
        ptr = last.next;
        for (int i = 0; i < length; i++){
            trips[i] = ptr.trip;
            ptr = ptr.next;
        }

        orderTripsByDept(trips, length);

        System.out.println("*List of charges ordered by department.");
        String currentDept = "";
        for (int i = 0; i < length; i++) {
            String tripDept = trips[i].getBooking().getEmployee().getDepartment().toString();
            if (!tripDept.equals(currentDept)) {
                currentDept = tripDept;
                System.out.println("--" + currentDept + "--");
            }
            System.out.println("\t" + trips[i].toString());
            //<*>Department total: $ 1,189.19
            int mileageUsed = trips[i].getEndMileage() - trips[i].getBeginMileage();
            System.out.println("\t\t[charge: $" + trips[i].getBooking().getVehicle().charge(mileageUsed) + "] [surcharge: " + trips[i].containsSurcharge() + "] [total charge: " + (trips[i].getBooking().getVehicle().charge(mileageUsed)) + trips[i].getBooking().getVehicle().surcharge(mileageUsed, trips[i].hasSurcharge()) + "]");
        }
        System.out.println("*end of util.\n");
    }
}
