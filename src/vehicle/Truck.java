package vehicle;

import rental.Date;
import rental.Make;

public class Truck extends Vehicle{
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
        return 0;
    }

    @Override
    public double surcharge(int mileageUsed, boolean surcharge) {
        return 0;
    }
}
