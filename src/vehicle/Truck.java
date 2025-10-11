package vehicle;

import rental.Date;
import rental.Make;

public class Truck extends Vehicle{
    private String type = "truck";
    private final double per_mile = 2.99;
    private final double flat_fee = 39.99;

    public Truck(String plate) {
        super(plate);
    }

    public Truck(String plate, Date obtained, Make make, int mileage) {
        super(plate, obtained, make, mileage);
    }

    public Truck(String[] dataToken) {
        super(dataToken);
    }

    @Override
    public double charge(int mileageUsed) {
        return mileageUsed * 2.99;
    }

    @Override
    public double surcharge(int mileageUsed, boolean surcharge) {
        return flat_fee;
    }
}
