package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;

/**
 * The ListFlights class represents a command to list all flights in the Flight Booking System.
 * It implements the Command interface and contains a method to execute the command.
 * 
 * <p>
 * When executed, this command retrieves a list of all flights from the FlightBookingSystem and
 * prints a summary of each flight, including its details in short format. It then prints the
 * total number of flights listed.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>{@code
 * ListFlights listFlightsCommand = new ListFlights();
 * listFlightsCommand.execute(flightBookingSystem);
 * }</pre>
 * </p>
 */

public class ListFlights implements Command {

	/**
     * Executes the command to list all flights in the Flight Booking System.
     * 
     * @param flightBookingSystem The FlightBookingSystem instance from which flights are listed.
     * @throws FlightBookingSystemException if an error occurs while listing flights.
     */
	
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
    	// Retrieve a list of all flights from the Flight Booking System.
        List<Flight> flights = flightBookingSystem.getFlights();
        
        // Print a summary of each flight's details in short format.
        for (Flight flight : flights) {
            System.out.println(flight.getDetailsShort());
        }
        
        // Print the total number of flights listed.
        System.out.println(flights.size() + " flight(s)");
    }
}
