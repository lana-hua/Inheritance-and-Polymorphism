package vehicle;

import rental.Date;
import rental.Make;

public class Utility extends Vehicle{
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
        return 0;
    }

    @Override
    public double surcharge(int mileageUsed, boolean surcharge) {
        return 0;
    }
}
