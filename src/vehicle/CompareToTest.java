package vehicle;

import org.junit.Before;
import org.junit.Test;
import rental.Campus;
import rental.Date;
import rental.Make;

import static org.junit.Assert.assertTrue;

public class CompareToTest {
    Sedan sedan1;
    Sedan sedan2;
    Sedan sedan3;
    Sedan sedan4;

    Utility utility1;
    Utility utility2;
    Utility utility3;
    Utility utility4;

    Truck truck1;
    Truck truck2;
    Truck truck3;
    Truck truck4;

    @Before
    public void setUp() throws Exception {
        Date date = new Date(10,10,2025);

        sedan1 = new Sedan("28462S", date, Make.CHEVY, 10293, Campus.Camden);
        sedan2 = new Sedan("28462S", date, Make.CHEVY, 10293, Campus.Camden);
        sedan3 = new Sedan("14252S", date, Make.TOYOTA, 9000, Campus.Camden);
        sedan4 = new Sedan("39732S", date, Make.HONDA, 28474, Campus.Camden);

        utility1 = new Utility("59362D", date, Make.FORD, 2348, Campus.Camden);
        utility2 = new Utility("59362D", date, Make.FORD, 2348, Campus.Camden);
        utility3 = new Utility("47920D", date, Make.CHEVY, 1384, Campus.Camden);
        utility4 = new Utility("63724D", date, Make.TOYOTA, 3984, Campus.Camden);

        truck1 = new Truck("28473X", date, Make.HONDA, 48826, Campus.Camden);
        truck2 = new Truck("28473X", date, Make.HONDA, 48826, Campus.Camden);
        truck3 = new Truck("13984X", date, Make.FORD, 38475, Campus.Camden);
        truck4 = new Truck("38479X", date, Make.CHEVY, 29485, Campus.Camden);

    }

    @Test
    public void compareToSedanLessThan() {
        assertTrue(sedan1.compareTo(sedan4) == -1);//test case 1 - compare two sedans that return -1

    }

    @Test
    public void compareToSedanGreaterThan() {
        assertTrue(sedan1.compareTo(sedan3) == 1);//test case 2 - compare two sedans that return 1
    }

    @Test
    public void compareToSedanEqual() {
        assertTrue(sedan1.compareTo(sedan2) == 0);//test case 3 - compare two sedans that return 0
    }

    @Test
    public void compareToUtilityLessThan() {
        assertTrue(utility1.compareTo(utility4) == -1);//test case 1 - compare two utility that return -1
    }

    @Test
    public void compareToUtilityGreaterThan() {
        assertTrue(utility1.compareTo(utility3) == 1);//test case 2 - compare two utility that return 1
    }

    @Test
    public void compareToUtilityEqual() {
        assertTrue(utility1.compareTo(utility2) == 0);//test case 3 - compare two utility that return 0
    }

    @Test
    public void compareToTruckLessThan(){
        assertTrue(truck1.compareTo(truck4) == -1);//test case 1 - compare two utility that return -1
    }

    @Test
    public void compareToTruckGreaterThan(){
        assertTrue(truck1.compareTo(truck3) == 1);//test case 2 - compare two utility that return 1
    }

    @Test
    public void compareToTruckEqual(){
        assertTrue(truck1.compareTo(truck2) == 0);//test case 3 - compare two utility that return 0
    }
}