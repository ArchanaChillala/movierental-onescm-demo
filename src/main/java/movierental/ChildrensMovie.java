package movierental;

public class ChildrensMovie extends Movie {
    
    private static final double BASE_CHARGE = 1.5;
    private static final int THRESHOLD_DAYS = 3;
    private static final double EXTRA_CHARGE_PER_DAY = 1.5;
    
    public ChildrensMovie(String title) {
        super(title);
    }
    
    @Override
    public double getCharge(int daysRented) {
        if (exceedsThreshold(daysRented))
            return chargeWithExtraDays(daysRented);
        return BASE_CHARGE;
    }

    private static double chargeWithExtraDays(int daysRented) {
        return BASE_CHARGE + extraCharge(daysRented);
    }

    private static double extraCharge(int daysRented) {
        return extra(daysRented) * EXTRA_CHARGE_PER_DAY;
    }

    private static int extra(int daysRented) {
        return daysRented - THRESHOLD_DAYS;
    }

    private static boolean exceedsThreshold(int daysRented) {
        return daysRented > THRESHOLD_DAYS;
    }
}
