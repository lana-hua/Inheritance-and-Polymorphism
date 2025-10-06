package rental;

/**
 * Fleet class that manages a collection of Vehicles.
 *  It allows the user to search, resize, add, remove, and match vehicles in the list.
 *  It also allows the user to print the fleet by make and date.
 * @author Lana Huang
 */
public class Fleet {
    private static final int CAPACITY = 4; //initial capacity
    private static final int NOT_FOUND = -1;
    private Vehicle[] fleet;
    private int size; //current number of vehicles in the fleet

    /**
     * Constructs a fleet with the initial capacity size, CAPACITY, and a size of 0.
     */
    public Fleet() {
        fleet = new Vehicle[CAPACITY]; // initialize array with starting capacity
        size = 0;
    }

    /**
     * Finds the index of the given vehicle in the fleet.
     * If it does not exist in the fleet it returns -1, NOT_FOUND.
     * @param vehicle The vehicle to be found in the fleet.
     * @return index if found; -1, NOT_FOUND otherwise.
     */
    private int find(Vehicle vehicle) {
        if (contains(vehicle)) {
            for (int i = 0; i < size; i++){
                if (fleet[i].getPlate().compareTo(vehicle.getPlate()) == 0){
                    return i;
                }
            }
        }
        return NOT_FOUND;
    }

    /**
     * Grows the array of the fleet by 4 if the fleet reaches capacity
     */
    private void grow() {
        Vehicle[] newArray = new Vehicle[size+4];

        if (size >= 0) System.arraycopy(fleet, 0, newArray, 0, fleet.length);

        fleet = newArray;
    }

    /**
     * Add given vehicle to the fleet.
     * If the fleet is already at capacity, call grow to increase capacity.
     * @param vehicle Vehicle to be added to the fleet.
     */
    public void add(Vehicle vehicle) {
        if (!contains(vehicle)) {
            if (size == fleet.length) {
                grow();
            }
            fleet[size] = vehicle;
            size++;
        }
    }

    /**
     * Remove the given vehicle from the fleet
     * It does nothing if vehicle is not in fleet. It overwrites with the last vehicle in the fleet
     * @param vehicle the vehicle to be removed from the fleet
     */
    public void remove(Vehicle vehicle) {
        int index = find(vehicle);
        if (index != NOT_FOUND){
            Frontend.printRemovedVehicleMessage(fleet[index]);
            fleet[index] = fleet[size - 1];
            fleet[size - 1] = null;
            size--;
        } else {
            Frontend.printNotInFleetMessage(vehicle);
        }
    }

    /**
     * Gets the vehicle given the license plate number from the fleet.
     * @param plate The plate number to be found in the fleet.
     * @return vehicle The vehicle found in the fleet.
     */
    public Vehicle getVehicle(String plate) {
        Vehicle vehicle = new Vehicle(plate);
        return fleet[find(vehicle)];
    }

    /**
     * Checks if the fleet contains a vehicle given the vehicle.
     * Checks the plate number to match the vehicle.
     * @param vehicle The vehicle being checked against the fleet.
     * @return true if the vehicle is found; false otherwise.
     */
    public boolean contains(Vehicle vehicle) {
        for (int i = 0; i < size; i++){
            if (fleet[i].getPlate().compareTo(vehicle.getPlate()) == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Prints the fleet by the make then by the date obtained
     * Uses selection sort methods to loop through the fleet to find the minimum index of the minimum element.
     */
    public void printByMake() {
        if (size == 0) {
            Frontend.printNoVehicleInFleet();

        } else {
            System.out.println("*List of vehicles in the fleet, ordered by make and date obtained.");
            for (int i = 0; i < size - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < size; j++) {
                    int compareMake = fleet[j].getMake().compareTo(fleet[minIndex].getMake());
                    if (compareMake < 0) {
                        minIndex = j;
                    } else if (compareMake == 0) {
                        int compareDate = fleet[j].getDate().compareTo(fleet[minIndex].getDate());
                        if (compareDate < 0) {
                            minIndex = j;
                        }
                    }

                }
                if (minIndex != i) {
                    Vehicle temp = fleet[i];
                    fleet[i] = fleet[minIndex];
                    fleet[minIndex] = temp;
                }
            }

            for (int i = 0; i < size; i++) {
                System.out.println(fleet[i]);
            }

            System.out.println("*end of list.\n");
        }

    }
}
