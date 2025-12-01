package movierental;

public class NewReleaseMovie extends Movie {
    
    private static final double CHARGE_PER_DAY = 3.0;
    private static final int BONUS_POINT_THRESHOLD = 1;
    private static final int BONUS_POINTS = 2;
    private static final int REGULAR_POINTS = 1;
    
    public NewReleaseMovie(String title) {
        super(title);
    }
    
    @Override
    public double getCharge(int daysRented) {
        return daysRented * CHARGE_PER_DAY;
    }
    
    @Override
    public int getFrequentRenterPoints(int daysRented) {
        return (daysRented > BONUS_POINT_THRESHOLD) ? BONUS_POINTS : REGULAR_POINTS;
    }
}
