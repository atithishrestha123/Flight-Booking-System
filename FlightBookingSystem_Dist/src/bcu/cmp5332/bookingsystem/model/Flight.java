package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The Flight class represents a flight in the booking system.
 * It contains information about the flight such as its ID, flight number, origin, destination,
 * departure date, capacity, price, passengers, and bookings.
 */

public class Flight {

    private int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private int capacity;
    private double price;

    private final Set<Customer> passengers;
    private final Set<Booking> bookings;
    
    /**
     * Constructs a new Flight object with the specified attributes.
     * 
     * @param id The ID of the flight.
     * @param flightNumber The flight number.
     * @param origin The origin airport or location.
     * @param destination The destination airport or location.
     * @param departureDate The departure date of the flight.
     * @param capacity The capacity of the flight (number of seats).
     * @param price The price of the flight.
     */

    public Flight(int id, String flightNumber, String origin, String destination, LocalDate departureDate, int capacity,
            double price) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.capacity = capacity;
        this.price = price;
        this.passengers = new HashSet<>();
        this.bookings = new HashSet<>();
    }

    /**
     * Gets the ID of the flight.
     * 
     * @return The ID of the flight.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the flight.
     * 
     * @param id The ID of the flight.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the flight number.
     * 
     * @return The flight number.
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Sets the flight number.
     * 
     * @param flightNumber The flight number.
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * Gets the origin airport or location.
     * 
     * @return The origin airport or location.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the origin airport or location.
     * 
     * @param origin The origin airport or location.
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Gets the destination airport or location.
     * 
     * @return The destination airport or location.
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination airport or location.
     * 
     * @param destination The destination airport or location.
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Gets the departure date of the flight.
     * 
     * @return The departure date of the flight.
     */
    public LocalDate getDepartureDate() {
        return departureDate;
    }

    /**
     * Sets the departure date of the flight.
     * 
     * @param departureDate The departure date of the flight.
     */
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Gets the capacity of the flight (number of seats).
     * 
     * @return The capacity of the flight.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity of the flight (number of seats).
     * 
     * @param capacity The capacity of the flight.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets the price of the flight.
     * 
     * @return The price of the flight.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the flight.
     * 
     * @param price The price of the flight.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns a list of passengers on this flight.
     * 
     * @return A list of passengers on this flight.
     */
    public List<Customer> getPassengers() {
        return new ArrayList<>(passengers);
    }

    /**
     * Returns a list of bookings for this flight.
     * 
     * @return A list of bookings for this flight.
     */
    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }

    /**
     * Adds a booking to this flight.
     * 
     * @param booking The booking to be added.
     */
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    /**
     * Returns a short string representation of the flight details.
     * 
     * @return A short string representation of the flight details.
     */
    public String getDetailsShort() {
        return "Flight " + flightNumber + " from " + origin + " to " + destination + ".";
    }

    /**
     * Generates a detailed string representation of the flight including flight details and passenger information.
     * 
     * @return A detailed string representation of the flight.
     */
    public String getDetailsLong() {

    	// Define a date formatter for formatting the departure date.
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");

        // Create a StringBuilder to construct the details string.
        StringBuilder details = new StringBuilder();

        // Append the header for flight details section.
        details.append("Flight Details:\n");
        details.append("\n----------------------\n");
        
        // Append specific details about the flight.
        details.append("Flight ID: ").append(id).append("\n");
        details.append("Flight Number: ").append(flightNumber).append("\n");
        details.append("Origin: ").append(origin).append("\n");
        details.append("Destination: ").append(destination).append("\n");
        details.append("Capacity: ").append(capacity).append("\n");
        details.append("Price: ").append(price).append("\n");
        details.append("Departure Date: ").append(departureDate.format(dtf)).append("\n");

        // Append a divider between flight details and passenger details.
        details.append("\n----------------------\n");
        details.append("\nPassenger Details:\n");
        
        // Check if there are passengers or bookings associated with this flight
        if (!passengers.isEmpty() || !bookings.isEmpty()) {
            
        	// If there are passengers or bookings, append details for each passenger
            for (Customer passenger : passengers) {
                details.append("- Name: ").append(passenger.getName()).append("\n");
                details.append("  Phone Number: ").append(passenger.getPhone()).append("\n");
                details.append("  Email: ").append(passenger.getEmail()).append("\n");
            }
        } else {
        	// If there are no passengers or bookings, indicate so
            details.append("\nNo passengers found for this flight.\n");

        }
        // Convert the StringBuilder to a string and return.
        return details.toString();
    }

    /**
     * Adds a passenger to the flight.
     * 
     * @param passenger The passenger to be added.
     */
    public void addPassenger(Customer passenger) {
        passengers.add(passenger);
    }

    /**
     * Adds a booking to the flight and adds the associated customer as a passenger.
     * 
     * @param booking The booking to be added.
     */
    public void addBookingForCustomer(Booking booking) {
        // Add the booking to the set of bookings for this flight
        bookings.add(booking);
        // Add the associated customer of the booking as a passenger for this flight
        addPassenger(booking.getCustomer());
    }

    /**
     * Removes a passenger from the flight.
     * 
     * @param passenger The passenger to be removed.
     */
    public void removePassenger(Customer passenger) {
        passengers.remove(passenger);
    }


    /**
     * Removes a booking from the flight and removes the associated customer as a passenger.
     * 
     * @param booking The booking to be removed.
     */
    public void removeBooking(Booking booking) {
        // Remove the booking from the set of bookings for this flight
        bookings.remove(booking);
        // Remove the associated customer of the booking as a passenger for this flight
        passengers.remove(booking.getCustomer());
    }


}