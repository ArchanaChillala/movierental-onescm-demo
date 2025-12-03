package movierental;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {

    private final String name;
    private final List<Rental> rentals = new ArrayList<>();
    private final RentalStatementFormatter textFormatter = new TextStatementFormatter();
    private final RentalStatementFormatter htmlFormatter = new HtmlStatementFormatter();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        return textFormatter.format(name, getUnmodifiableRentals());
    }

    public String htmlStatement() {
        return htmlFormatter.format(name, getUnmodifiableRentals());
    }

    private List<Rental> getUnmodifiableRentals() {
        return Collections.unmodifiableList(rentals);
    }
}
