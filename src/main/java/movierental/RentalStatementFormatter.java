package movierental;

import java.util.List;
import java.util.stream.Collectors;

public class RentalStatementFormatter {

    private static final String RENTAL_RECORD_TEXT = "Rental Record for";
    private static final String AMOUNT_OWED_TEXT = "Amount owed is";
    private static final String YOU_EARNED_TEXT = "You earned";
    private static final String FREQUENT_RENTER_POINTS_TEXT = "frequent renter points";
    
    private static final String TEXT_LINE_FORMAT = "\t%s\t%.1f\n";
    private static final String TEXT_HEADER_FORMAT = RENTAL_RECORD_TEXT + " %s\n";
    private static final String TEXT_FOOTER_FORMAT = AMOUNT_OWED_TEXT + " %s\n" + YOU_EARNED_TEXT + " %d " + FREQUENT_RENTER_POINTS_TEXT;
    
    private static final String HTML_TABLE_ROW_FORMAT = "  <tr><td>%s</td><td>%.1f</td></tr>";
    private static final String HTML_HEADER_FORMAT = "<h1>" + RENTAL_RECORD_TEXT + " <em>%s</em></h1>\n";
    private static final String HTML_TABLE_FORMAT = "<table>\n%s\n</table>\n";
    private static final String HTML_FOOTER_FORMAT = "<p>" + AMOUNT_OWED_TEXT + " <em>%.1f</em></p>\n<p>" + YOU_EARNED_TEXT + " <em>%d</em> " + FREQUENT_RENTER_POINTS_TEXT + "</p>";

    public String formatTextStatement(String customerName, List<Rental> rentals) {
        return formatTextHeader(customerName) + formatTextBody(rentals) + formatTextFooter(rentals);
    }

    public String formatHtmlStatement(String customerName, List<Rental> rentals) {
        return formatHtmlHeader(customerName) + formatHtmlBody(rentals) + formatHtmlFooter(rentals);
    }

    public String formatTextLine(Rental rental) {
        return String.format(TEXT_LINE_FORMAT, rental.getMovieTitle(), rental.getCharge());
    }

    public String formatHtmlTableRow(Rental rental) {
        return String.format(HTML_TABLE_ROW_FORMAT, rental.getMovieTitle(), rental.getCharge());
    }

    private String formatTextHeader(String customerName) {
        return String.format(TEXT_HEADER_FORMAT, customerName);
    }

    private String formatTextBody(List<Rental> rentals) {
        return rentals.stream()
                .map(this::formatTextLine)
                .collect(Collectors.joining());
    }

    private String formatTextFooter(List<Rental> rentals) {
        return String.format(TEXT_FOOTER_FORMAT, calculateTotalAmount(rentals), calculateTotalFrequentRenterPoints(rentals));
    }

    private String formatHtmlHeader(String customerName) {
        return String.format(HTML_HEADER_FORMAT, customerName);
    }

    private String formatHtmlBody(List<Rental> rentals) {
        if (rentals.isEmpty()) {
            return "";
        }
        return wrapInHtmlTable(generateHtmlTableRows(rentals));
    }

    private String generateHtmlTableRows(List<Rental> rentals) {
        return rentals.stream()
                .map(this::formatHtmlTableRow)
                .collect(Collectors.joining("\n"));
    }

    private String wrapInHtmlTable(String tableRows) {
        return String.format(HTML_TABLE_FORMAT, tableRows);
    }

    private String formatHtmlFooter(List<Rental> rentals) {
        return String.format(HTML_FOOTER_FORMAT, calculateTotalAmount(rentals), calculateTotalFrequentRenterPoints(rentals));
    }

    private double calculateTotalAmount(List<Rental> rentals) {
        return rentals.stream()
                .mapToDouble(Rental::getCharge)
                .sum();
    }

    private int calculateTotalFrequentRenterPoints(List<Rental> rentals) {
        return rentals.stream()
                .mapToInt(Rental::getFrequentRenterPoints)
                .sum();
    }
}
