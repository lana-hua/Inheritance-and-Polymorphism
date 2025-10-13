package vehicle;

import rental.Campus;
import rental.Date;
import rental.Make;

public class Utility extends Vehicle{
    private final String type = "utility";
    private final double chargePerMile = 1.99;
    private final double surchargePerMile = .30;
    private final double surchargeMax = 35.99;

    public Utility(String plate) {
        super(plate);
    }

    public Utility() {
        super();
    }

    public Utility(String plate, Date obtained, Make make, int mileage, Campus campus) {
        super(plate, obtained, make, mileage, campus);
    }

    public Utility(String[] dataToken) {
        super(dataToken);
    }

    public double getSurchargeMax() {
        return surchargeMax;
    }

    @Override
    public double charge(int mileageUsed) {
        return mileageUsed * chargePerMile;
    }

    @Override
    public double surcharge(int mileageUsed, boolean surcharge) {
        double cost = mileageUsed * surchargePerMile;
        if (surcharge) {
            if (cost > surchargeMax) {
                return surchargeMax;
            }
            return cost;
        } else { return 0.0; }

    }

    public String getType() {
        return type;
    }
}
