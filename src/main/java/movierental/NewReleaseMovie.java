package movierental;

public class NewReleaseMovie extends Movie {
    
    private static final double CHARGE_PER_DAY = 3.0;
    
    public NewReleaseMovie(String title) {
        super(title);
    }
    
    @Override
    public double getCharge(int daysRented) {
        return daysRented * CHARGE_PER_DAY;
    }
}
