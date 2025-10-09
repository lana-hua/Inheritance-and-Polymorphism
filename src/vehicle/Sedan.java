package vehicle;

import rental.Campus;
import util.Date;
import rental.Make;

public class Sedan extends Vehicle{
    private static final double SEDAN_RATE = 1.79;
    private static final double SEDA_SURCHARGE = 0.25;

    public Sedan(String plate, String type, Date obtained, Make make, int mileage, Campus campus) {
        super(plate, "sedan", obtained, make, mileage, campus);
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
