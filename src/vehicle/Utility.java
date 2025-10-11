package vehicle;

import rental.Date;
import rental.Make;

public class Utility extends Vehicle{
    private final String type = "utility";
    private final double charge_per_mile = 1.99;
    private final double surcharge_per_mile = .30;
    private final double surcharge_max = 35.99;

    public Utility(String plate) {
        super(plate);
    }

    public Utility(String plate, Date obtained, Make make, int mileage) {
        super(plate, obtained, make, mileage);
    }

    public Utility(String[] dataToken) {
        super(dataToken);
    }

    @Override
    public double charge(int mileageUsed) {
        return mileageUsed * charge_per_mile;
    }

    @Override
    public double surcharge(int mileageUsed, boolean surcharge) {
        double cost = mileageUsed * surcharge_per_mile;
        if (cost > surcharge_max) {
            return surcharge_max;
        }
        return cost;
    }

    public String getType() {
        return type;
    }
}
