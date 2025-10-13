package rental;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class IsValidDateTest {
    @Test
    public void isValidDayRange() {
        Date date1 = new Date("11/34/2025");
        assertFalse(date1.isValid());
    }

    @Test
    public void isValidMonthRange() {

        Date date2 = new Date("19/16/2025");
        assertFalse(date2.isValid());
    }

    @Test
    public void isValidYearRange() {
        Date date3 = new Date("06/07/-1");
        assertFalse(date3.isValid());
    }

    @Test
    public void isValidLeapYearDate() {
        Date date4 = new Date("02/29/2026");
        assertFalse(date4.isValid());
    }

    @Test
    public void isValidCorrectLeapYearDate() {
        Date date5 = new Date("02/29/2028");
        assertTrue(date5.isValid());
    }

    @Test
    public void isValidDate() {
        Date date6 = new Date("10/30/2025");
        assertTrue(date6.isValid());
    }
}