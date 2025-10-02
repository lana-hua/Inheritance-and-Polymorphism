package rental;

/**
 * Enum make that contains all the different makes of the vehicles
 * @author Lana Huang
 */
public enum Make {
    CHEVY,
    FORD,
    HONDA,
    TOYOTA;

    /**
     * Checks if the given string is a valid make.
     * Accounts for various different cases.
     * @param make The string make that needs to be validated.
     * @return true if the string is a valid make; false otherwise.
     */
    public static boolean isValidMake(String make) {
        String upperMake = make.toUpperCase();
        switch (upperMake) {
            case "HONDA", "CHEVY", "TOYOTA", "FORD" -> {
                return true;
            }
            default -> {
                Frontend.printInvalidMakeMessage(make);
                return false;
            }
        }
    }
}


