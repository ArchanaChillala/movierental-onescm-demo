package movierental;

import java.util.List;
import java.util.stream.Collectors;

public class HtmlStatementFormatter extends RentalStatementFormatter {

    private static final String TABLE_ROW_FORMAT = "  <tr><td>%s</td><td>%.1f</td></tr>";
    private static final String HEADER_FORMAT = "<h1>" + RENTAL_RECORD_TEXT + " <em>%s</em></h1>\n";
    private static final String TABLE_FORMAT = "<table>\n%s\n</table>\n";
    private static final String FOOTER_FORMAT = "<p>" + AMOUNT_OWED_TEXT + " <em>%.1f</em></p>\n<p>" + YOU_EARNED_TEXT + " <em>%d</em> " + FREQUENT_RENTER_POINTS_TEXT + "</p>";

    @Override
    public String formatLine(Rental rental) {
        return String.format(TABLE_ROW_FORMAT, rental.getMovieTitle(), rental.getCharge());
    }

    @Override
    protected String formatHeader(String customerName) {
        return String.format(HEADER_FORMAT, customerName);
    }

    @Override
    protected String formatBody(List<Rental> rentals) {
        if (rentals.isEmpty()) {
            return "";
        }
        return wrapInTable(generateTableRows(rentals));
    }

    private String generateTableRows(List<Rental> rentals) {
        return rentals.stream()
                .map(this::formatLine)
                .collect(Collectors.joining("\n"));
    }

    private String wrapInTable(String tableRows) {
        return String.format(TABLE_FORMAT, tableRows);
    }

    @Override
    protected String formatFooter(List<Rental> rentals) {
        return String.format(FOOTER_FORMAT,
                calculateTotalAmount(rentals),
                calculateTotalFrequentRenterPoints(rentals));
    }
}
