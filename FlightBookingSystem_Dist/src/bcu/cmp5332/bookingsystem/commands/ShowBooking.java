package bcu.cmp5332.bookingsystem.commands;

import java.util.List;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The ShowBooking class implements the Command interface and represents a command to display bookings
 * associated with a specific customer in the Flight Booking System.
 * It retrieves the customer's details along with the bookings made by the customer.
 * If no bookings are found for the specified customer, it indicates so.
 */

public class ShowBooking implements Command {

	private final int customerId; // The ID of the customer whose bookings are to be displayed
    private StringBuilder result; // A StringBuilder to store the result of the command

    /**
     * Constructs a ShowBooking object with the specified customer ID.
     *
     * @param customerId The ID of the customer.
     */
    
    public ShowBooking(int customerId) {
        this.customerId = customerId;
        this.result = new StringBuilder();
    }
    
    /**
     * Executes the ShowBooking command, displaying the bookings associated with the specified customer.
     *
     * @param fbs the FlightBookingSystem instance.
     * @throws FlightBookingSystemException if there is an error accessing the Flight Booking System.
     */

    @Override
    public void execute(FlightBookingSystem fbs) throws FlightBookingSystemException {
    	// Retrieve the customer object by ID from the Flight Booking System.
    	Customer customer = fbs.getCustomerByID(customerId);

        if (customer == null) {
        	// If the customer is not found, append a message indicating so to the result StringBuilder.
            result.append("Customer With ID ").append(customerId).append(" is not found.\n");
            return;
        }
        // Append the details of the found customer to the result StringBuilder
        result.append(customer.getDetailsLong()).append("\n");

     // Retrieve all the bookings from the Flight Booking System.
        List<Booking> bookings = fbs.getBookings();
        int flightsCount = 0; // Counter to keep track of the number of bookings associated with the customer.

        // Iterate through each booking to find bookings associated with the specified customer.
        for (Booking booking : bookings) {
            if (booking.getCustomer().getId() == customerId) {
                Flight flight = booking.getFlight();
             // If the booking is associated with the customer, append its details to the result StringBuilder.
                result.append("Booking ID: ").append(booking.getId()).append("\n");
                result.append("----------Flight Details------------- \n");
                result.append("ID: ").append(flight.getId()).append("\n");
                result.append("Flight no: ").append(flight.getFlightNumber()).append("\n");
                result.append("Origin: ").append(flight.getOrigin()).append("\n");
                result.append("Destination: ").append(flight.getDestination()).append("\n");
                result.append("Number of seats: ").append(flight.getCapacity()).append("\n");
                result.append("Price: ").append(flight.getPrice()).append("\n");
                result.append("Departure Date: ").append(flight.getDepartureDate()).append("\n\n");
                System.out.println();
                flightsCount++;
            }
        }
        // If no bookings are found for the customer, append a message indicating so to the result StringBuilder
        if (flightsCount == 0) {
            result.append("No flights booked with customerID #").append(customerId).append(".\n");
        } else {
            // If bookings are found, append the count of bookings to the result StringBuilder
            result.append(flightsCount).append(" booking(s)\n");
        }
    }

    /**
     * Gets the result of the ShowBooking command as a String.
     *
     * @return the result of the ShowBooking command as a String
     */
    public String getResult() {
        return result.toString();
    }
}
