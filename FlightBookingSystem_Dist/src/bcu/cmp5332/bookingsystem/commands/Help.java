package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The Help class represents a command to display help information for the Flight Booking System.
 * It implements the Command interface and contains a method to execute the command.
 * 
 * <p>
 * When executed, this command prints a predefined help message containing information
 * about available commands and their usage within the Flight Booking System.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>{@code
 * Help helpCommand = new Help();
 * helpCommand.execute(flightBookingSystem);
 * }</pre>
 * </p>
 */

public class Help implements Command {
	
	/**
     * Executes the command to display help information for the Flight Booking System.
     * 
     * @param flightBookingSystem The FlightBookingSystem instance for which help information is displayed
     */

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) {
    	
    	// Print the predefined help message containing information about available commands
        System.out.println(Command.HELP_MESSAGE);
    }
}
