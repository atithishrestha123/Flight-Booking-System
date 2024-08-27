package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.commands.Command;

import java.io.*;

/**
 * The Main class is the entry point of the Flight Booking System application.
 * It initializes the system, reads user input, and executes corresponding
 * commands.
 */

public class Main {

    /**
     * The main method is the entry point of the application.
     * It initializes the Flight Booking System, reads user input, and executes
     * commands.
     *
     * @param args The command-line arguments (not used in this application).
     * @throws IOException                  If an I/O error occurs while reading
     *                                      input from the console or storing data.
     * @throws FlightBookingSystemException If an error occurs within the Flight
     *                                      Booking System.
     */

    public static void main(String[] args) throws IOException, FlightBookingSystemException {

        // Load the Flight Booking System data from the file.
        FlightBookingSystem fbs = FlightBookingSystemData.load();

        // Initialize the BufferedReader to read input from the console.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Display the welcome message and available commands to the user.
        System.out.println("Flight Booking System");
        System.out.println("Enter 'help' to see a list of available commands.");

        // Continuously prompt the user for input until 'exit' command is entered.
        while (true) {
            System.out.print("> ");
            String line = br.readLine();
            if (line.equals("exit")) {
                break; // Exit the loop if 'exit' command is entered
            }

            try {
                // Parse the user input and execute the corresponding command
                Command command = CommandParser.parse(line);
                command.execute(fbs);
            } catch (FlightBookingSystemException ex) {
                // Catch and display any exceptions that occur during command execution
                System.out.println(ex.getMessage());
            }
        }
        // Store the updated Flight Booking System data back to the file
        FlightBookingSystemData.store(fbs);

        // Exit the application with status code 0 (success)
        System.exit(0);
    }
}
