package movierental;

import java.util.List;

public abstract class RentalStatementFormatter {

    protected static final String RENTAL_RECORD_TEXT = "Rental Record for";
    protected static final String AMOUNT_OWED_TEXT = "Amount owed is";
    protected static final String YOU_EARNED_TEXT = "You earned";
    protected static final String FREQUENT_RENTER_POINTS_TEXT = "frequent renter points";

    public String format(String customerName, List<Rental> rentals) {
        return formatHeader(customerName) + formatBody(rentals) + formatFooter(rentals);
    }

    protected abstract String formatHeader(String customerName);

    protected abstract String formatBody(List<Rental> rentals);

    protected abstract String formatFooter(List<Rental> rentals);

    public abstract String formatLine(Rental rental);

    protected double calculateTotalAmount(List<Rental> rentals) {
        return rentals.stream()
                .mapToDouble(Rental::getCharge)
                .sum();
    }

    protected int calculateTotalFrequentRenterPoints(List<Rental> rentals) {
        return rentals.stream()
                .mapToInt(Rental::getFrequentRenterPoints)
                .sum();
    }
}
