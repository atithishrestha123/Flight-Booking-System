package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.commands.LoadGUI;
import bcu.cmp5332.bookingsystem.commands.ShowBooking;
import bcu.cmp5332.bookingsystem.commands.ShowCustomer;
import bcu.cmp5332.bookingsystem.commands.ShowFlight;
import bcu.cmp5332.bookingsystem.commands.ListFlights;
import bcu.cmp5332.bookingsystem.commands.AddFlight;
import bcu.cmp5332.bookingsystem.commands.CancelBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.EditBooking;
import bcu.cmp5332.bookingsystem.commands.Help;
import bcu.cmp5332.bookingsystem.commands.ListCustomers;
import bcu.cmp5332.bookingsystem.commands.AddBooking;
import bcu.cmp5332.bookingsystem.commands.AddCustomer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The CommandParser class is responsible for parsing user input and converting it into executable commands.
 * It reads user input from the console and generates corresponding Command objects based on the input.
 */

/**
 * Constructs a CommandParser object.
 */
public class CommandParser {


	/**
     * Parses the user input and generates the corresponding Command object.
     * 
     * @param line The user input to be parsed.
     * @return the Command object corresponding to the user input.
     * @throws IOException if an I/O error occurs while reading input from the console.
     * @throws FlightBookingSystemException if the user input is invalid or cannot be parsed.
     */
    public static Command parse(String line) throws IOException, FlightBookingSystemException {
        try {
        	// Split the user input into parts based on spaces.
            String[] parts = line.split(" ", 3);
            String cmd = parts[0];
            
            // Check the command and create corresponding Command object.
            if (cmd.equals("addflight")) {
            	// Prompt the user for flight details and create an AddFlight command 
            	// to add a new flight to the system
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                
                // Prompt the user to enter the flight number and read the input.
                System.out.print("Flight Number: ");              
                String flighNumber = reader.readLine();
                
                // Prompt the user to enter the origin and read the input.
                System.out.print("Origin: ");
                String origin = reader.readLine();
                
                // Prompt the user to enter the destination and read the input.
                System.out.print("Destination: ");
                String destination = reader.readLine();

                // Prompt the user to enter the number of seats and read the input.
                System.out.print("Number of Seats: ");
                int capacity = Integer.parseInt(reader.readLine());
                
                // Prompt the user to enter the price and read the input.
                System.out.print("Price: ");
                double price = Double.parseDouble(reader.readLine());

                // Parse the departure date with multiple attempts allowed.
                LocalDate departureDate = parseDateWithAttempts(reader);

                // Return a new AddFlight command with the provided flight details
                return new AddFlight(flighNumber, origin, destination, departureDate, capacity, price);
            } else if (cmd.equals("addcustomer")) {
            	// Prompt the user for customer details and create an AddCustomer command
                // to add a new customer to the system
            	
            	// Initialize a BufferedReader to read input from the console.
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                
                // Prompt the user to enter the customer's name and read the input.
                System.out.print("Name: ");
                String name = reader.readLine();
                
                // Prompt the user to enter the customer's phone number and read the input.
                System.out.print("Phone: ");
                String phone = reader.readLine();
                
                // Prompt the user to enter the customer's email address and read the input.
                System.out.print("Email: ");
                String email = reader.readLine();

                // Return a new AddCustomer command with the provided customer details.
                return new AddCustomer(name, phone, email);
            } else if (cmd.equals("loadgui")) {
            	// Create a LoadGUI command to load the graphical user interface.
                return new LoadGUI();
            } else if (parts.length == 1) {
            	// Check for commands without arguments.
                if (line.equals("listflights")) {
                	
                	// Create a ListFlights command to display all flights.
                    return new ListFlights();
                } else if (line.equals("listcustomers")) {
                	
                	// Create a ListCustomers command to display all customers.
                    return new ListCustomers();

                } else if (line.equals("help")) {
                	
                	// Create a Help command to display help information.
                    return new Help();
                }
            } else if (parts.length == 2) {
            	
            	// Check for commands with one argument.
                int id = Integer.parseInt(parts[1]);

                if (cmd.equals("showflight")) {
                	// Create a ShowFlight command to display details of a specific flight.
                    int flightId = Integer.parseInt(parts[1]);
                    return new ShowFlight(flightId);

                } else if (cmd.equals("showcustomer")) {
                	// Create a ShowCustomer command to display details of a specific customer.
                    int customerId = Integer.parseInt(parts[1]);
                    return new ShowCustomer(customerId);

                } else if (cmd.equals("showbooking")) {
                	// Create a ShowBooking command to display details of bookings for a specific customer.
                    int customerId = Integer.parseInt(parts[1]);
                    return new ShowBooking(customerId);
                }
            } else if (parts.length == 3) {
            	
            	// Check for commands with two arguments.
                if (cmd.equals("addbooking")) {
                	
                	// Create an AddBooking command to add a booking for a customer on a flight.
                    int customerId = Integer.parseInt(parts[1]);
                    int flightId = Integer.parseInt(parts[2]);
                    return new AddBooking(customerId, flightId);

                } else if (cmd.equals("editbooking")) {
                	
                	 // Create an EditBooking command to edit an existing booking.
                    int bookingId = Integer.parseInt(parts[1]);
                    int flightId = Integer.parseInt(parts[2]);
                    return new EditBooking(bookingId, flightId);

                } else if (cmd.equals("cancelbooking")) {
                	// Create a CancelBooking command to cancel a booking.
                    int customerId = Integer.parseInt(parts[1]);
                    int flightId = Integer.parseInt(parts[2]);
                    return new CancelBooking(customerId, flightId);

                }
            }
        } catch (NumberFormatException ex) {
        	// If a number parsing error occurs, catch the exception and handles it.
        	System.out.println("Error: Invalid number format.");
        }

     // If the command cannot be parsed or is invalid, throw an exception
        throw new FlightBookingSystemException("Invalid command.");
    }
    
    /**
     * Parses a date input from the console with multiple attempts allowed.
     * 
     * @param br The BufferedReader to read input from the console.
	 * @param attempts The number of attempts allowed to enter a valid date.
	 * @return The parsed LocalDate object representing the date entered by the user.
	 * @throws IOException If an I/O error occurs while reading input from the console.
	 * @throws FlightBookingSystemException If an invalid date is entered after the specified number of attempts.
	 * @throws IllegalArgumentException If the number of attempts is less than 1.
	 */
    private static LocalDate parseDateWithAttempts(BufferedReader br, int attempts)
            throws IOException, FlightBookingSystemException {
    	// Check if the number of attempts is valid.
        if (attempts < 1) {
            throw new IllegalArgumentException("Number of attempts should be higher that 0");
        }
        
        // Loop through the attempts to parse the date.
        while (attempts > 0) {
            attempts--; // Decrease the number of attempts.
            System.out.print("Departure Date (\"YYYY-MM-DD\" format): ");
            try {
            	
            	// Attempt to parse the date entered by the user
                LocalDate departureDate = LocalDate.parse(br.readLine());
                return departureDate; // Return the parsed date if successful.
            } catch (DateTimeParseException dtpe) {
            	// Catch exception if the entered date format is incorrect.
                System.out.println("Date must be in YYYY-MM-DD format. " + attempts + " attempts remaining...");
            }
        }
        
        // If no valid date is entered after all attempts, throw an exception.
        throw new FlightBookingSystemException("Incorrect departure date provided. Cannot create flight.");
    }
    
    /**
     * Parses a date input from the console with 3 attempts allowed.
     * 
     * @param br The BufferedReader to read input from the console
     * @return the parsed LocalDate object representing the date entered by the user
     * @throws IOException if an I/O error occurs while reading input from the console
     * @throws FlightBookingSystemException if an invalid date is entered after 3 attempts
     */

    private static LocalDate parseDateWithAttempts(BufferedReader br) throws IOException, FlightBookingSystemException {
        return parseDateWithAttempts(br, 3);
    }
}
