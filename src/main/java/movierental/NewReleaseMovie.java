package movierental;

public class NewReleaseMovie extends Movie {
    
    public NewReleaseMovie(String title) {
        super(title);
    }
    
    @Override
    public int getPriceCode() {
        return Movie.NEW_RELEASE;
    }
    
    @Override
    public double getCharge(int daysRented) {
        return daysRented * 3;
    }
    
    @Override
    public int getFrequentRenterPoints(int daysRented) {
        // add bonus for a two day new release rental
        return (daysRented > 1) ? 2 : 1;
    }
}
