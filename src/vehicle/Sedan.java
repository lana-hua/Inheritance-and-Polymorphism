package vehicle;

import rental.Date;
import rental.Make;

public class Sedan extends Vehicle{
    private String type = "sedan";
    private final double chargePerMile = 1.79;
    private final double surchargePerMile = .25;
    private final double surchargeMax = 32.99;

    public Sedan(String plate) {
        super(plate);
    }

    public Sedan(String plate, Date obtained, Make make, int mileage, String type) {
        super(plate, obtained, make, mileage);
        this.type = type;

    }

    public Sedan(String[] dataToken) {
        super(dataToken);
    }

    @Override
    public double charge(int mileageUsed) {
        return chargePerMile * mileageUsed;
    }

    @Override
    public double surcharge(int mileageUsed, boolean surcharge) {
        double cost = mileageUsed * surchargePerMile;
        if (cost > surchargeMax) {
            return surchargeMax;
        }
        return cost;

    }
}
