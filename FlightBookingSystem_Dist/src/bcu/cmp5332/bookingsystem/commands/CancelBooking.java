package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.*;
import java.util.ArrayList;

/**
 * The CancelBooking class represents a command to cancel a booking for a customer on a specific flight.
 * It implements the Command interface and contains a method to execute the command.
 * 
 * <p>
 * When executed, this command cancels the booking associated with the specified customer ID
 * and flight ID in the FlightBookingSystem. If the booking is successfully canceled, it is removed
 * from the system. If the booking does not exist or cannot be cancelled for any reason, an exception
 * is thrown.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>{@code
 * int customerId = 1234;
 * int flightId = 5678;
 * CancelBooking cancelBookingCommand = new CancelBooking(customerId, flightId);
 * cancelBookingCommand.execute(flightBookingSystem);
 * }</pre>
 * </p>
 */

public class CancelBooking implements Command {

    private final int customerId;
    private final int flightId;
    
    /**
     * Constructs a CancelBooking object with the specified customer ID and flight ID.
     * 
     * @param customerId The ID of the customer whose booking is to be cancelled.
     * @param flightId The ID of the flight for which the booking is to be cancelled.
     */

    public CancelBooking(int customerId, int flightId) {
        this.customerId = customerId;
        this.flightId = flightId;
    }
    
    /**
     * Executes the command to cancel a booking for the specified customer on the specified flight.
     * 
     * @param flightBookingSystem The FlightBookingSystem instance in which the booking is cancelled.
     * @throws FlightBookingSystemException if an error occurs while canceling the booking.
     */

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
    	
    	// Cancel the booking associated with the specified customer ID and flight ID
        flightBookingSystem.cancelBooking(customerId, flightId);
    }
}