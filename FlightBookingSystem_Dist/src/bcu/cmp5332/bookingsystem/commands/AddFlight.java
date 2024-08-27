package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import bcu.cmp5332.bookingsystem.main.*;
import java.time.LocalDate;
import java.util.List;

/**
 * The AddFlight class represents a command to add a new flight to the Flight Booking System.
 * It implements the Command interface and contains a method to execute the command.
 * 
 * <p>
 * When executed, this command creates a new Flight object with the specified details,
 * including flight number, origin, destination, departure date, capacity, and price.
 * The flight is then added to the FlightBookingSystem. If the addition is successful,
 * a notification is printed. If any error occurs during the process, it is wrapped
 * in a FlightBookingSystemException and thrown.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>{@code
 * String flightNumber = "ABC123";
 * String origin = "New York";
 * String destination = "London";
 * LocalDate departureDate = LocalDate.of(2024, 3, 15);
 * int capacity = 150;
 * double price = 500.00;
 * AddFlight addFlightCommand = new AddFlight(flightNumber, origin, destination, departureDate, capacity, price);
 * addFlightCommand.execute(flightBookingSystem);
 * }</pre>
 * </p>
 */

public class AddFlight implements Command {

    private final String flightNumber;
    private final String origin;
    private final String destination;
    private final LocalDate departureDate;
    private final int capacity;
    private final double price;
    
    /**
     * Constructs an AddFlight object with the specified details.
     * 
     * @param flightNumber The Flight Number.
     * @param origin The Origin Airport.
     * @param destination The Destination Airport.
     * @param departureDate The Departure Date of the Flight.
     * @param capacity The Capacity of the Flight.
     * @param price The Price of the Flight.
     * @throws IllegalArgumentException if any input parameter is null
     */

    public AddFlight(String flightNumber, String origin, String destination, LocalDate departureDate, int capacity,
            double price) {
        if (flightNumber == null || origin == null || destination == null || departureDate == null) {
            throw new IllegalArgumentException("Invalid input parameters for AddFlight");
        }

        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.capacity = capacity;
        this.price = price;
    }
    
    /**
     * Executes the command to add a new flight to the Flight Booking System.
     * 
     * @param flightBookingSystem The FlightBookingSystem instance to which the flight is added.
     * @throws FlightBookingSystemException if an error occurs while adding the flight.
     */

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        try {
            List<Flight> flights = flightBookingSystem.getFlights();

            int maxId = 0;
            for (Flight existingFlight : flights) {
                if (existingFlight.getId() > maxId) {
                    maxId = existingFlight.getId();
                }
            }

            // Creates a new Flight with a unique ID.
            Flight newFlight = new Flight(++maxId, flightNumber, origin, destination, departureDate, capacity, price);

            // Adds the new Flight to the system.
            flightBookingSystem.addFlight(newFlight);

            // Print a notification.
            System.out.println("Flight #" + newFlight.getId() + " added.");
        } catch (Exception e) {
        	
            // Catch any exceptions and wrap them in FlightBookingSystemException
            throw new FlightBookingSystemException("Error adding flight: " + e.getMessage());
        }
    }
}
