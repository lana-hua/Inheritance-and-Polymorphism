package vehicle;

import rental.Campus;
import rental.Date;
import rental.Make;

public class Truck extends Vehicle{
    private String type = "truck";
    private final double perMile = 2.99;
    private final double flatFee = 39.99;

    public Truck(String plate) {
        super(plate);
    }

    public Truck() {
        super();
    }

    public double getFlatFee() {
        return flatFee;
    }

    public Truck(String plate, Date obtained, Make make, int mileage, Campus campus) {
        super(plate, obtained, make, mileage, campus);
    }


    public Truck(String[] dataToken) {
        super(dataToken);
    }

    @Override
    public double charge(int mileageUsed) {
        return mileageUsed * perMile;
    }

    @Override
    public double surcharge(int mileageUsed, boolean surcharge) {
        if (surcharge) {
            return flatFee;
        } else { return 0.0;}

    }
}
