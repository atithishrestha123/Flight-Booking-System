package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.time.LocalDate;

/**
 * The EditBooking class represents a command to edit a booking in the Flight Booking System.
 * It implements the Command interface and contains a method to execute the command.
 * 
 * <p>
 * This command allows editing the flight associated with a booking. It takes the ID of the booking
 * to be edited and the ID of the new flight to which the booking should be moved. When executed,
 * it retrieves the booking and the old flight associated with it, validates their existence,
 * removes the booking from the old flight, and creates a new booking for the customer on the
 * specified new flight with the current date. It then updates the flight and customer objects
 * accordingly and prints a confirmation message.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>{@code
 * int bookingId = 123;
 * int newFlightId = 456;
 * EditBooking editBookingCommand = new EditBooking(bookingId, newFlightId);
 * editBookingCommand.execute(flightBookingSystem);
 * }</pre>
 * </p>
 */

public class EditBooking implements Command {

    private int bookingId;
    private int flightId;
    
    /**
     * Constructs an EditBooking object with the specified booking ID and new flight ID.
     * 
     * @param bookingId The ID of the booking to be edited.
     * @param flightId The ID of the new flight to which the booking should be moved.
     */

    public EditBooking(int bookingId, int flightId) {
        this.bookingId = bookingId;
        this.flightId = flightId;
    }
    
    /**
     * Executes the command to edit a booking in the Flight Booking System.
     * 
     * @param fbs the FlightBookingSystem instance in which the booking is being edited.
     */

    @Override
    public void execute(FlightBookingSystem fbs) {
        try {
        	
        	// Retrieve the booking to be edited.
            Booking booking = fbs.getBookingById(bookingId);
            
            // Check if the booking exists.
            if (booking == null) {
                System.out.println("Booking with ID " + bookingId + " not found. Booking cannot be edited.");
                return;
            }

            // Retrieve the old flight associated with the booking.
            Flight oldFlight = booking.getFlight();
            
            // Check if the old flight exists.
            if (oldFlight == null) {
                System.out.println("Flight for booking with ID " + bookingId + " not found. Booking cannot be edited.");
                return;
            }

            // Retrieve the customer associated with the booking.
            Customer customer = booking.getCustomer();

            // Retrieve the new flight to which the booking should be moved.
            Flight newFlight = fbs.getFlightByID(flightId);
            
            // Check if the new flight exists.
            if (newFlight == null) {
                System.out.println("Flight with ID " + flightId + " not found. Booking cannot be edited.");
                return;
            }

            // Remove old booking.
            oldFlight.removePassenger(customer);
            oldFlight.removeBooking(booking);
            customer.removeBooking(booking);
            fbs.removeBooking(booking);

            // Create new booking for the customer on the new flight with the current date.
            Booking newBooking = new Booking(customer, newFlight, LocalDate.now());
            fbs.addBooking(newBooking);
            newFlight.addPassenger(customer);
            newFlight.addBookingForCustomer(newBooking);
            System.out.println("Booking was updated successfully.");
        } catch (FlightBookingSystemException e) {
            System.out.println("Error while processing booking update: " + e.getMessage());
        }
    }
}
