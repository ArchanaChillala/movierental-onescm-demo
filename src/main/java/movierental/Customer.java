package movierental;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {

    private final String name;
    private final List<Rental> rentals = new ArrayList<>();
    private final RentalStatementFormatter formatter = new RentalStatementFormatter();

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
        return formatter.formatTextStatement(name, getUnmodifiableRentals());
    }

    public String htmlStatement() {
        return formatter.formatHtmlStatement(name, getUnmodifiableRentals());
    }

    private List<Rental> getUnmodifiableRentals() {
        return Collections.unmodifiableList(rentals);
    }
}
