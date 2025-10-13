package util;

import rental.*;
import java.text.DecimalFormat;
import vehicle.Vehicle;


public class Sort {
    private Sort() {}

    /**
     * PC Command: Prints the cost report, ordered by department, including the charge and surcharge for each trip, and the department total for all charges.
     */
    public static void printCost(){
        if (Frontend.tripList.getLast() == null) {
            System.out.println("There is no archived trips for the cost report.");
            return;
        }

        Trip[] trips = getAllTrips();
        orderTripsByDept(trips, trips.length);
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("*List of charges ordered by department.");
        String currentDept = "";
        double deptTotal = 0.00;

        for (int i = 0; i < trips.length; i++) {
            String tripDept = trips[i].getBooking().getEmployee().getDepartment().toString();
            if (!tripDept.equals(currentDept)) {
                if (!currentDept.isEmpty()){
                    System.out.println("  <*>Department total: $ " + df.format(deptTotal));
                    deptTotal = 0.00;
                }
                currentDept = tripDept;
                System.out.println("--" + currentDept + "--");
            }
            int mileageUsed = trips[i].getEndMileage() - trips[i].getBeginMileage();

            if (trips[i].hasSurcharge()){
                double tripTotal = trips[i].getBooking().getVehicle().charge(mileageUsed) + trips[i].getBooking().getVehicle().surcharge(mileageUsed, trips[i].hasSurcharge());
                deptTotal += tripTotal;
                System.out.println("\t" + (trips[i].getBooking().getVehicle().getPlate() + " " + trips[i].getBooking().getBegin() + " ~ " + trips[i].getBooking().getEnd() + " mileage(old): " + trips[i].getBeginMileage() + " mileage(new): " + trips[i].getEndMileage() + " mileage(used): " + mileageUsed + " [dropped off: " + trips[i].getBooking().getCampusDropoff().name() + "**]"));
                System.out.println("\t\t[charge: $" + df.format(trips[i].getBooking().getVehicle().charge(mileageUsed)) + "] [surcharge: $" + df.format(trips[i].getBooking().getVehicle().surcharge(mileageUsed, trips[i].hasSurcharge())) + "] [total charge: $ " + df.format(tripTotal) + "]");
            }
            else {
                deptTotal += trips[i].getBooking().getVehicle().charge(mileageUsed);
                System.out.println("\t" + (trips[i].getBooking().getVehicle().getPlate() + " " + trips[i].getBooking().getBegin() + " ~ " + trips[i].getBooking().getEnd() + " mileage(old): " + trips[i].getBeginMileage() + " mileage(new): " + trips[i].getEndMileage() + " mileage(used): " + mileageUsed + " [dropped off: " + trips[i].getBooking().getCampusDropoff().name() + "]"));
                System.out.println("\t\t[charge: $" + df.format(trips[i].getBooking().getVehicle().charge(mileageUsed)) + "] [surcharge: no] [total charge: $ " + df.format(trips[i].getBooking().getVehicle().charge(mileageUsed)) + "]");
            }
        }
        System.out.println("  <*>Department total: $ " + df.format(deptTotal));
        System.out.println("*end of util.\n");
    }

    /**
     * Print all completed trips in the circular linked list, tripList, ordered by end date.
     * This method creates a visited boolean array that keeps track of Nodes already visited.
     * It then repeatedly finds the unvisited node with the earliest date, prints the trip information.
     * Then it marks the node as visited and continues this process until all are printed.
     */
    public static void printCompletedTrips() {
        if (Frontend.tripList.getLast() == null) {
            System.out.println("There is no completed trips.");
            return;
        }
        System.out.println("*List of completed trips ordered by ending date.");

        int length = 1;
        Node ptr = Frontend.tripList.getLast().getNext();

        while (ptr != Frontend.tripList.getLast()) {
            length++;
            ptr = ptr.getNext();
        }

        boolean[] visited = new boolean[length];

        for (int i = 0; i < length; i++) {
            Node minNode = null;
            int minIndex = -1;

            ptr = Frontend.tripList.getLast().getNext();
            for (int j = 0; j < length; j++) {
                if (!visited[j]) {
                    if (minNode == null || (ptr.getTrip().getBooking().getEnd().compareTo(minNode.getTrip().getBooking().getEnd()) < 0)) {
                        minNode = ptr;
                        minIndex = j;
                    }
                }
                ptr = ptr.getNext();
            }
            System.out.println(minNode.getTrip().toString());
            visited[minIndex] = true;

        }
        System.out.println("*end of list.\n");
    }

    /**
     * Puts the list of trips in order by department
     * @param trips the list of trips that will be organized
     * @param length the length of the list of trips
     */
    private static void orderTripsByDept(Trip[] trips, int length) {
        for (int i = 0; i < (length - 1); i++) {
            for (int j = 0; j < (length - i - 1); j++) {
                String dept1 = trips[j].getBooking().getEmployee().getDepartment().toString();
                String dept2 = trips[j + 1].getBooking().getEmployee().getDepartment().toString();
                //sort departments
                if (dept1.compareTo(dept2) > 0) {
                    Trip temp = trips[j];
                    trips[j] = trips[j+1];
                    trips[j+1] = temp;
                }
            }
        }
    }

    /**
     * Puts all the trips from the linked list into an array
     * @return array of trips
     */
    private static Trip[] getAllTrips() {
        int length = 1;
        Node ptr = Frontend.tripList.getLast().getNext();
        while (ptr != Frontend.tripList.getLast()) {
            length++;
            ptr = ptr.getNext();
        }

        Trip[] trips = new Trip[length];
        ptr = Frontend.tripList.getLast().getNext();
        for (int i = 0; i < length; i++){
            trips[i] = ptr.getTrip();
            ptr = ptr.getNext();
        }

        return trips;
    }

    /**
     * PR Command: Prints all reservations ordered by campus city location, then license plate number, and then by beginning date.
     */
    public static void printBookingsByCity() {
        if (Frontend.bookings.isEmpty()) {
            System.out.println("There is no booking record.");
            return;
        }

        for (int i = 0; i < (Frontend.bookings.size() - 1); i++) {
            for (int j = 0; j < (Frontend.bookings.size() - i - 1); j++) {

                String city1 = Frontend.bookings.get(j).getVehicle().getCampus().getCity();
                String city2 = Frontend.bookings.get(j + 1).getVehicle().getCampus().getCity();

                int compareCampus = city1.compareTo(city2); //Compare by campus first
                if (compareCampus > 0) {
                    swapBookings(j, j + 1);
                }

                else if (city1.compareTo(city2) == 0) {
                    String plate1 = Frontend.bookings.get(j).getVehicle().getPlate();
                    String plate2 = Frontend.bookings.get(j + 1).getVehicle().getPlate();

                    if (plate1.compareTo(plate2) > 0) {
                        swapBookings(j, (j + 1));
                    }
                    else if (plate1.compareTo(plate2) == 0) {
                        Date begin1 = Frontend.bookings.get(j).getBegin();
                        Date begin2 = Frontend.bookings.get(j + 1).getBegin();

                        if (begin1.compareTo(begin2) > 0) {
                            swapBookings(j, (j + 1));
                        }
                    }
                }
            }
        }

        System.out.println("*List of reservations ordered by location/license plate/beginning date.");
        for (int i = 0; i < Frontend.bookings.size(); i++) {
            System.out.println(Frontend.bookings.get(i).toString());
        }
        System.out.println("*end of util.\n");
    } //ordered by city, then plate, and then beginning date

    /**
     * PD Command: Prints all reservations ordered by department and then by employee.
     */
    public static void printBookingsByDept() {
        if (Frontend.bookings.isEmpty()) {
            System.out.println("There is no booking record.");
            return;
        }

        for (int i = 0; i < (Frontend.bookings.size() - 1); i++) {
            for (int j = 0; j < (Frontend.bookings.size() - i - 1); j++) {
                String dept1 = Frontend.bookings.get(j).getEmployee().getDepartment().toString();
                String dept2 = Frontend.bookings.get(j + 1).getEmployee().getDepartment().toString();
                //sort departments
                if (dept1.compareTo(dept2) > 0) {
                    Booking temp = Frontend.bookings.get(j);
                    Frontend.bookings.set(j, Frontend.bookings.get(j + 1));
                    Frontend.bookings.set(j + 1, temp);
                } else if (dept1.compareTo(dept2) == 0) {
                    String emp1 = Frontend.bookings.get(j).getEmployee().name();
                    String emp2 = Frontend.bookings.get(j + 1).getEmployee().name();
                    //sort employees in department
                    if (emp1.compareTo(emp2) > 0) {
                        Booking temp = Frontend.bookings.get(j);
                        Frontend.bookings.set(j, Frontend.bookings.get(j + 1));
                        Frontend.bookings.set(j + 1, temp);
                    }
                }
            }
        }
        System.out.println("*List of reservations ordered by department and employee.");
        String currentDept = "";
        for (int i = 0; i < Frontend.bookings.size(); i++) {
            String bookingDept = Frontend.bookings.get(i).getEmployee().getDepartment().toString();
            if (!bookingDept.equals(currentDept)) {
                currentDept = bookingDept;
                System.out.println("--" + currentDept + "--");
            }
            System.out.println("\t" + Frontend.bookings.get(i).toString());
        }
        System.out.println("*end of util.\n");
    } //ordered by department then by employee

    /**
     * Swap 2 bookings in the bookings list given the index of booking 1 and booking 2.
     * @param i The index of the first booking to be swapped.
     * @param j The index of the second booking to be swapped.
     */
    private static void swapBookings(int i, int j) {
        Booking temp = Frontend.bookings.get(i);
        Frontend.bookings.set(i, Frontend.bookings.get(j));
        Frontend.bookings.set(j, temp);
    }

    /**
     * Prints the fleet by the make then by the date obtained
     * Uses selection sort methods to loop through the fleet to find the minimum index of the minimum element.
     */
    public static void printSortedFleet() {
        if (Frontend.fleet.isEmpty()) {
            Frontend.printNoVehicleInFleet();

        } else {
            System.out.println("*List of vehicles in the fleet, ordered by location/make/date obtained.");
            for (int i = 0; i < Frontend.fleet.size() - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < Frontend.fleet.size(); j++) {
                    int compareCampus = Frontend.fleet.get(j).getCampus().getCity().compareTo(Frontend.fleet.get(minIndex).getCampus().getCity()); //Compare by campus first
                    if (compareCampus < 0) {
                        minIndex = j;
                    } else if (compareCampus == 0) {
                        int compareMake = Frontend.fleet.get(j).getMake().compareTo(Frontend.fleet.get(minIndex).getMake()); //If campus is equal then compare by car make
                        if (compareMake < 0) {
                            minIndex = j;
                        } else if (compareMake == 0) {
                            int compareDate = Frontend.fleet.get(j).getDate().compareTo(Frontend.fleet.get(minIndex).getDate()); //If car make is equal then compare by date
                            if (compareDate < 0) {
                                minIndex = j;
                            }
                        }
                    }
                }

                if (minIndex != i) {
                    Vehicle temp = Frontend.fleet.get(i);
                    Frontend.fleet.set(i, Frontend.fleet.get(minIndex));
                    Frontend.fleet.set(minIndex, temp);
                }
            }

            for (int i = 0; i < Frontend.fleet.size(); i++) {
                System.out.println(Frontend.fleet.get(i));
            }

            System.out.println("*end of util.\n");
        }
    }
}
