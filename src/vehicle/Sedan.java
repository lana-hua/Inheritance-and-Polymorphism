package vehicle;

import rental.Date;
import rental.Make;

public class Sedan extends Vehicle{
    private String type = "sedan";
    private final double charge_per_mile = 1.79;
    private final double surcharge_per_mile = .25;
    private final double surcharge_max = 32.99;

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
        return 0;
    }

    @Override
    public double surcharge(int mileageUsed, boolean surcharge) {
        return 0;
    }
}
