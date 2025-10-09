package vehicle;

import rental.Date;
import rental.Frontend;
import rental.Make;

/**
 Vehicle class that contains information about the vehicle.
 It contains the string license plate, the Date it was obtained, the make of the car, and the mileage on the odometer.
 @author Lana Huang
 */
public class Vehicle implements Comparable<Vehicle> {
    private String plate; //license plate number
    private Date obtained; //Date class described in the next page
    private Make make; //Make is an enum class
    private int mileage; //current reading on the odometer

    /**
     * Gets the mileage from an instance of Vehicle.
     * @return mileage
     */
    public int getMileage() {
        return mileage;
    }

    /**
     * Sets the mileage from an instance of Vehicle with the given mileage.
     * @param mileage The mileage that will be set to.
     */
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    /**
     * Gets the plate from an instance of Vehicle.
     * @return plate
     */
    public String getPlate() {
        return plate;
    }

    /**
     * Gets the make from an instance of Vehicle.
     * @return make
     */
    public Make getMake() {
        return make;
    }

    /**
     * Gets date from an instance of Vehicle.
     * @return date
     */
    public Date getDate() {
        return obtained;
    }

    /**
     * Constructs a Vehicle
     * @param plate
     */
    public Vehicle(String plate) {
        this.plate = plate;
        this.obtained = null;
        this.make = null;
        this.mileage = 0;
    }

    /**
     * Constructs Vehicle given the plate, date obtained, make, and mileage.
     * @param plate String license plate number.
     * @param obtained Date obtained.
     * @param make Make of the vehicle.
     * @param mileage Mileage of the vehicle.
     */
    public Vehicle(String plate, Date obtained, Make make, int mileage) {
        this.plate = plate;
        this.obtained = obtained;
        this.make = make;
        this.mileage = mileage;
    }

    /**
     * Constructs Vehicle given a String array dataToken.
     * Checks if each of the dataTokens are valid.
     * @param dataToken DataToken that contains the plate, date, make, and mileage
     */
    public Vehicle(String[] dataToken) {
        if (Vehicle.isValidVehicle(dataToken)){
            this.plate = dataToken[1];
            this.obtained = new Date(dataToken[2]);
            this.make = Make.valueOf(dataToken[3].toUpperCase());
            this.mileage = Integer.parseInt(dataToken[4]);
        }
    }

    /**
     * Checks if each of the dataToken can make a valid vehicle.
     * @param dataToken String array dataToken with plate, date, make, and mileage.
     * @return true if all string array elements are valid vehicle parts; false otherwise.
     */
    public static boolean isValidVehicle(String[] dataToken) {
        Date obtained = new Date(dataToken[2]);
        int mileage = Integer.parseInt(dataToken[4]);

        //check date
        if (!obtained.isCalendarDateValid(dataToken[2])) {
            return false;
        } else if (!Make.isValidMake(dataToken[3])) {
            return false;
        } else if (!Vehicle.isValidMileage(mileage)) {
            return false;
        } else { return true; }
    }

    /**
     * Checks if the plate string is a valid plate.
     * Checks the character amount, if the first 5 characters are digit and if the last char is equal to S, D, or X.
     * @param plate The string plate that is checked.
     * @return true if it's a valid plate; false otherwise.
     */
    public static boolean isValidPlate(String plate) {
        plate = plate.trim();
        if (plate.length() != 6){
            return false;
        }
        for (int i = 0; i < plate.length(); i++) {
            if(i != 5 && !Character.isDigit(plate.charAt(i))){
                return false;
            }
            else if (plate.charAt(i) != 'S' || plate.charAt(i) != 'D' || plate.charAt(i) != 'X'){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the mileage is greater than 0.
     * @param mileage The mileage to be checked.
     * @return true if the mileage is greater than 0; false otherwise.
     */
    public static boolean isValidMileage(int mileage) {
        if (mileage > 0) {
            return true;
        }
        else {
            Frontend.printInvalidMileageMessage(mileage);
            return false;
        }
    }

    /**
     * Override equals method that checks if the vehicles are equal.
     * Checks through the getClass method and through the vehicle plate.
     * @param o   the reference object with which to compare.
     * @return true if vehicle is the same; false if they are different.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle other = (Vehicle) o;
        return this.plate.equals(other.plate);
    }

    /**
     * Override the toString method to return the plate, make, date obtained, and the mileage.
     * @return a string of all the vehicle traits together.
     */
    @Override
    public String toString() {
        return plate + ":" + make + ":" + obtained + " [mileage:" + mileage + "]";
    }

    /**
     * Override compareTo method that compares the plate of two vehicles.
     * @param o the reference object with which to compare to.
     * @return 0 if they are the same; -1 or 1 if they are different.
     */
    @Override
    public int compareTo(Vehicle o) {
        return plate.compareTo(o.plate);
    }

    /**
     * Testbed for 3 test cases of Testing Specifications for compareTo method.
     * @param args
     */
    public static void main(String[] args) {
        //1 output
        Vehicle vehicle1 = new Vehicle("80671S", null, Make.CHEVY, 10293);
        Vehicle vehicle2 = new Vehicle("71707X", null, Make.CHEVY, 10293);
        System.out.println(vehicle1.compareTo(vehicle2));

        //0 output
        Vehicle vehicle3 = new Vehicle("58718D", null, Make.CHEVY, 10293);
        Vehicle vehicle4 = new Vehicle("58718D", null, Make.CHEVY, 10293);
        System.out.println(vehicle3.compareTo(vehicle4));

        //-1 output
        Vehicle vehicle5 = new Vehicle("58718D", null, Make.CHEVY, 10293);
        Vehicle vehicle6 = new Vehicle("65402A", null, Make.CHEVY, 10293);
        System.out.println(vehicle5.compareTo(vehicle6));


    }
}
