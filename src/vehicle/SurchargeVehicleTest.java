package vehicle;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import rental.*;

public class SurchargeVehicleTest {
    Sedan sedan;
    Utility utility;
    Truck truck;
    Date date;

    @Before
    public void setUp() throws Exception {
        date = new Date(10,10,2025);
        sedan = new Sedan("28462S", date, Make.CHEVY, 10293, Campus.Busch);
        utility = new Utility("59362D", date, Make.FORD, 2348, Campus.Camden);
        truck = new Truck("28473X", date, Make.HONDA, 48826, Campus.Livingston);


    }

    @Test
    public void noSurchargeSedan() {
        Booking sedanBooking = new Booking(date, date, sedan, Employee.Lim, Campus.Busch);
        Trip sedanTrip = new Trip(sedanBooking, sedan.getMileage(),10938);
        double sedanCost = sedan.surcharge(sedanTrip.mileageUsed(), sedanTrip.hasSurcharge());
        assertTrue(sedanCost == 0 && !sedanTrip.hasSurcharge());
    }

    @Test
    public void noSurchargeUtility() {
        Booking utilityBooking = new Booking(date, date, utility, Employee.Lim, Campus.Camden);
        Trip utilityTrip = new Trip(utilityBooking, utility.getMileage(),10938);
        double utilityCost = utility.surcharge(utilityTrip.mileageUsed(), utilityTrip.hasSurcharge());
        assertTrue(utilityCost == 0 && !utilityTrip.hasSurcharge());
    }

    @Test
    public void noSurchargeTruck() {
        Booking truckBooking = new Booking(date, date, truck, Employee.Lim, Campus.Livingston);
        Trip truckTrip = new Trip(truckBooking, truck.getMileage(),10938);
        double truckCost = truck.surcharge(truckTrip.mileageUsed(), truckTrip.hasSurcharge());
        assertTrue(truckCost == 0 && !truckTrip.hasSurcharge());

    }

    @Test
    public void lessThanMaxSurchargeSedan() {
        Booking sedanBooking = new Booking(date, date, sedan, Employee.Lim, Campus.Livingston);
        Trip sedanTrip = new Trip(sedanBooking, sedan.getMileage(),10294);
        double sedanCost = sedan.surcharge(sedanTrip.mileageUsed(), sedanTrip.hasSurcharge());
        assertTrue(sedanCost != 0 && sedanCost < sedan.getSurchargeMax() && sedanTrip.hasSurcharge());
    }

    @Test
    public void lessThanMaxSurchargeUtility() {
        Booking utilityBooking = new Booking(date, date, utility, Employee.Lim, Campus.Busch);
        Trip utilityTrip = new Trip(utilityBooking, utility.getMileage(),2349);
        double utilityCost = utility.surcharge(utilityTrip.mileageUsed(), utilityTrip.hasSurcharge());
        assertTrue(utilityCost != 0 && utilityCost < utility.getSurchargeMax() && utilityTrip.hasSurcharge());
    }

    @Test
    public void greaterThanMaxSurchargeSedan() {
        Booking sedanBooking = new Booking(date, date, sedan, Employee.Lim, Campus.Livingston);
        Trip sedanTrip = new Trip(sedanBooking, sedan.getMileage(),120348);
        double sedanCost = sedan.surcharge(sedanTrip.mileageUsed(), sedanTrip.hasSurcharge());
        assertTrue(sedanCost == sedan.getSurchargeMax() && sedanTrip.hasSurcharge());
    }

    @Test
    public void greaterThanMaxSurchargeUtility() {
        Booking utilityBooking = new Booking(date, date, utility, Employee.Lim, Campus.Busch);
        Trip utilityTrip = new Trip(utilityBooking, utility.getMileage(),397421);
        double utilityCost = utility.surcharge(utilityTrip.mileageUsed(), utilityTrip.hasSurcharge());
        assertTrue(utilityCost == utility.getSurchargeMax() && utilityTrip.hasSurcharge());

    }

    @Test
    public void truckSurcharge() {
        Booking truckBooking = new Booking(date, date, truck, Employee.Lim, Campus.Busch);
        Trip truckTrip = new Trip(truckBooking, truck.getMileage(),2983742);
        double truckCost = truck.surcharge(truckTrip.mileageUsed(), truckTrip.hasSurcharge());
        assertTrue(truckCost == truck.getFlatFee() && truckTrip.hasSurcharge());
    }


}