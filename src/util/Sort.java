package util;

import rental.Frontend;
import vehicle.Vehicle;

public class Sort {
    private Sort() {}


    /**
     * Prints the fleet by the make then by the date obtained
     * Uses selection sort methods to loop through the fleet to find the minimum index of the minimum element.
     */
    public static void printSortedFleet(List<Vehicle> fleet) {
        if (fleet.isEmpty()) {
            Frontend.printNoVehicleInFleet();

        } else {
            System.out.println("*List of vehicles in the fleet, ordered by location/make/date obtained.");
            for (int i = 0; i < fleet.size() - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < fleet.size(); j++) {
                    int compareCampus = fleet.get(j).getCampus().getCity().compareTo(fleet.get(minIndex).getCampus().getCity()); //Compare by campus first
                    if (compareCampus < 0) {
                        minIndex = j;
                    } else if (compareCampus == 0) {
                        int compareMake = fleet.get(j).getMake().compareTo(fleet.get(minIndex).getMake()); //If campus is equal then compare by car make
                        if (compareMake < 0) {
                            minIndex = j;
                        } else if (compareMake == 0) {
                            int compareDate = fleet.get(j).getDate().compareTo(fleet.get(minIndex).getDate()); //If car make is equal then compare by date
                            if (compareDate < 0) {
                                minIndex = j;
                            }
                        }
                    }
                }

                if (minIndex != i) {
                    Vehicle temp = fleet.get(i);
                    fleet.set(i, fleet.get(minIndex));
                    fleet.set(minIndex, temp);
                }
            }

            for (int i = 0; i < fleet.size(); i++) {
                System.out.println(fleet.get(i));
            }

            System.out.println("*end of util.\n");
        }
    }
}
