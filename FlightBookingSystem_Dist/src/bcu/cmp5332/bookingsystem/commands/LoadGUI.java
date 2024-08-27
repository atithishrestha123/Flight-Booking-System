package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import bcu.cmp5332.bookingsystem.gui.MainWindow;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

/**
 * The LoadGUI class represents a command to load the graphical user interface (GUI) of the Flight Booking System.
 * It implements the Command interface and contains a method to execute the command.
 * 
 * <p>
 * When executed, this command creates a new instance of the MainWindow class, which initializes and displays
 * the graphical user interface for the Flight Booking System using the provided FlightBookingSystem instance.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>{@code
 * LoadGUI loadGUICommand = new LoadGUI();
 * loadGUICommand.execute(flightBookingSystem);
 * }</pre>
 * </p>
 */

public class LoadGUI implements Command {

	/**
     * Executes the command to load the graphical user interface (GUI) of the Flight Booking System.
     * 
     * @param flightBookingSystem The FlightBookingSystem instance for which the GUI is loaded.
     * @throws FlightBookingSystemException if an error occurs while loading the GUI.
     */
	
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
    	
    	// Create and display the main window of the GUI using the provided FlightBookingSystem instance.
        new MainWindow(flightBookingSystem);
    }

}
