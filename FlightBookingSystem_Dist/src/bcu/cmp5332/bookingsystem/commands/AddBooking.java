package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.*;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import java.time.LocalDate;

/**
 * The AddBooking class represents a command to add a booking to the Flight
 * Booking System.
 * It implements the Command interface and contains methods to execute the
 * command.
 * 
 * <p>
 * A booking is created for a specific customer on a specific flight with the
 * current date.
 * The execute method carries out the necessary operations to add the booking to
 * the system,
 * including validating the customer and flight, creating the booking,
 * associating the booking
 * with the customer and flight, and updating relevant data structures within
 * the system.
 * </p>
 * 
 * <p>
 * This class takes two parameters - the ID of the customer for whom the booking
 * is being added
 * and the ID of the flight for which the booking is being added. It then
 * executes the necessary
 * operations to add the booking, handling any exceptions that may occur during
 * the process.
 * </p>
 * 
 * <p>
 * Example usage:
 * 
 * <pre>{@code
 * int customerId = 1234;
 * int flightId = 5678;
 * AddBooking addBookingCommand = new AddBooking(customerId, flightId);
 * addBookingCommand.execute(flightBookingSystem);
 * }</pre>
 * </p>
 */

public class AddBooking implements Command {

    private int customerId;
    private int flightId;

    /**
     * Constructs an AddBooking object with the specified customer ID and flight ID.
     * 
     * @param customerId The ID of the customer for whom the booking is being added.
     * @param flightId   The ID of the flight for which the booking is being added.
     */
    public AddBooking(int customerId, int flightId) {
        this.customerId = customerId;
        this.flightId = flightId;
    }

    /**
     * Executes the command to add a booking to the Flight Booking System.
     * 
     * @param fbs The FlightBookingSystem instance to which the booking is being
     *            added.
     */
    @Override
    public void execute(FlightBookingSystem fbs) {
        try {
            // Retrieve customer and flight objects based on provided IDs.
            Customer customer = fbs.getCustomerByID(customerId);
            Flight flight = fbs.getFlightByID(flightId);

            // Check if customer or flight is not found.
            if (customer == null || flight == null) {
                System.out.println("Customer or Flight not found. Booking cannot be added.");
                return;
            }

            // Create a booking for the customer on the flight with the current date.
            Booking booking = new Booking(customer, flight, LocalDate.now());

            // Add the booking to the Flight Booking System.
            fbs.addBooking(booking);

            // Add the booking to the customer's list of bookings.
            customer.addBooking(booking);

            // Add the customer as a passenger on the flight.
            flight.addPassenger(customer);

            // Associate the booking with the customer for the flight.
            flight.addBookingForCustomer(booking);

            System.out.println("Booking was issued successfully to the customer.");
        } catch (FlightBookingSystemException e) {
            System.out.println("Error while processing booking: " + e.getMessage());
        }
    }
}