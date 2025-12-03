package movierental;

import java.util.List;
import java.util.stream.Collectors;

public class TextStatementFormatter extends RentalStatementFormatter {

    private static final String LINE_FORMAT = "\t%s\t%.1f\n";
    private static final String HEADER_FORMAT = RENTAL_RECORD_TEXT + " %s\n";
    private static final String FOOTER_FORMAT = AMOUNT_OWED_TEXT + " %s\n" + YOU_EARNED_TEXT + " %d " + FREQUENT_RENTER_POINTS_TEXT;

    @Override
    public String formatLine(Rental rental) {
        return String.format(LINE_FORMAT, rental.getMovieTitle(), rental.getCharge());
    }

    @Override
    protected String formatHeader(String customerName) {
        return String.format(HEADER_FORMAT, customerName);
    }

    @Override
    protected String formatBody(List<Rental> rentals) {
        return rentals.stream()
                .map(this::formatLine)
                .collect(Collectors.joining());
    }

    @Override
    protected String formatFooter(List<Rental> rentals) {
        return String.format(FOOTER_FORMAT,
                calculateTotalAmount(rentals),
                calculateTotalFrequentRenterPoints(rentals));
    }
}
