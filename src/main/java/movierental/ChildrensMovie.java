package movierental;

public class ChildrensMovie extends Movie {
    
    public ChildrensMovie(String title) {
        super(title);
    }
    
    @Override
    public int getPriceCode() {
        return Movie.CHILDRENS;
    }
    
    @Override
    public double getCharge(int daysRented) {
        double amount = 1.5;
        if (daysRented > 3)
            amount += (daysRented - 3) * 1.5;
        return amount;
    }
}
