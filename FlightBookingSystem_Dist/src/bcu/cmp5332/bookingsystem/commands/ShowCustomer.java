package bcu.cmp5332.bookingsystem.commands;

import java.util.List;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The ShowCustomer class represents a command to display detailed information
 * about a specific customer
 * and their bookings in the Flight Booking System.
 * It implements the Command interface and contains a method to execute the
 * command.
 * 
 * <p>
 * When executed, this command retrieves the customer with the specified ID from
 * the FlightBookingSystem
 * and prints their detailed information, including name, phone, email, and
 * bookings. It then lists
 * all bookings associated with the customer, including the booking date and
 * details of the corresponding flights.
 * If the customer has no bookings, it prints a message indicating that no
 * bookings are found.
 * </p>
 * 
 * <p>
 * Example usage:
 * 
 * <pre>{@code
 * int customerId = 1234;
 * ShowCustomer showCustomerCommand = new ShowCustomer(customerId);
 * showCustomerCommand.execute(flightBookingSystem);
 * }</pre>
 * </p>
 */

public class ShowCustomer implements Command {

    private final int customerId;
    private StringBuilder result;

    /**
     * Constructs a ShowCustomer object with the specified customer ID.
     * 
     * @param customerId The ID of the customer to be displayed.
     */

    public ShowCustomer(int customerID) {
        this.customerId = customerID;
        this.result = new StringBuilder();
    }

    /**
     * Executes the command to display detailed information about the specified
     * customer
     * and their bookings in the Flight Booking System.
     * 
     * @param flightBookingSystem The FlightBookingSystem instance from which the
     *                            customer and bookings are retrieved.
     * @throws FlightBookingSystemException if an error occurs while retrieving or
     *                                      displaying the customer and bookings.
     */

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        Customer customer = flightBookingSystem.getCustomerByID(customerId);
        if (customer != null) {
            result.append(customer.getDetailsLong()).append("\n");
        }

        List<Booking> bookings = flightBookingSystem.getBookings();
        int bookingsCount = 0;

        for (Booking booking : bookings) {
            if (booking.getCustomer().getId() == customerId) {
                Flight flight = booking.getFlight();
                result.append("* Booking date: ").append(booking.getBookingDate())
                        .append(" for Flight #").append(flight.getId()).append(" - ")
                        .append(flight.getFlightNumber()).append(" - ")
                        .append(flight.getOrigin()).append(" to ").append(flight.getDestination())
                        .append(" on ").append(flight.getDepartureDate()).append("\n");
                bookingsCount++;
            }
        }

        if (bookingsCount == 0) {
            result.append("No bookings found.\n");
        } else {
            result.append(bookingsCount).append(" booking(s)\n");
        }
    }

    /**
     * Gets the result of the ShowCustomer command as a String.
     * 
     * @return The result of the ShowCustomer command as a String.
     */
    public String getResult() {
        return result.toString();
    }
}
