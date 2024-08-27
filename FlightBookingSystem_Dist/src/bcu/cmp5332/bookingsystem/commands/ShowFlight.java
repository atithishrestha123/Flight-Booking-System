package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The ShowFlight class represents a command to display detailed information
 * about a specific flight.
 * It implements the Command interface and contains a method to execute the
 * command.
 * 
 * <p>
 * When executed, this command retrieves the flight with the specified ID from
 * the FlightBookingSystem
 * and prints its detailed information. If no flight is found with the provided
 * ID, it prints a message
 * indicating that there is no flight with that ID.
 * </p>
 * 
 * <p>
 * Example usage:
 * 
 * <pre>{@code
 * int flightId = 1234;
 * ShowFlight showFlightCommand = new ShowFlight(flightId);
 * showFlightCommand.execute(flightBookingSystem);
 * }</pre>
 * </p>
 */

public class ShowFlight implements Command {

    private final int flightId;
    private StringBuilder result;

    /**
     * Constructs a ShowFlight object with the specified flight ID.
     * 
     * @param flightId The ID of the flight to be displayed.
     */

    public ShowFlight(int flightId) {
        this.flightId = flightId;
        this.result = new StringBuilder();
    }

    /**
     * Executes the command to display detailed information about the specified
     * flight.
     * 
     * @param flightBookingSystem The FlightBookingSystem instance from which the
     *                            flight is retrieved.
     * @throws FlightBookingSystemException if an error occurs while retrieving or
     *                                      displaying the flight.
     */

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        Flight flight = flightBookingSystem.getFlightByID(flightId);
        if (flight != null) {
        	// Print the details of the flight.
            System.out.println(flight.getDetailsLong());
            
         // Append the details of the flight to the result StringBuilder.
            result.append(flight.getDetailsLong()).append("\n");
        } else {
        	
        	// If no flight is found with the provided ID, append a message indicating so to the result StringBuilder.
            result.append("Sorry, no flights available with ID").append(flightId).append(".\n");
        }

    }

    /**
     * Gets the result of the ShowFlight command as a String.
     * 
     * @return The result of the ShowFlight command as a String.
     */
    public String getResult() {
        return result.toString();
    }
}