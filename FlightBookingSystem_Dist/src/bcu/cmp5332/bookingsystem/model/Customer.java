package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;

// import bcu.cmp5332.bookingsystem.model.Booking;

import java.util.ArrayList;
import java.util.List;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

	/**
	 * The Customer class represents a customer in the Flight Booking System. It contains
	 * information such as the customer's ID, name, phone number, email, and a list of bookings.
	 */
public class Customer {

    private int id; // The unique identifier for the customer.
    private String name; // The name of the customer.
    private String phone; // The phone number of the customer.
    private List<Booking> bookings; // The list of bookings made by the customer.
    private String email; // The email address of the customer.

    /**
     * Constructs a new Customer object with the specified ID, name, phone number, and email.
     *
     * @param id The unique identifier for the customer.
     * @param name The name of the customer.
     * @param phone The phone number of the customer.
     * @param email The email address of the customer.
     */

    public Customer(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.bookings = new ArrayList<>(); // Initialize the list of bookings
        this.email = email;
    }

    /**
     * Returns the unique identifier for the customer.
     *
     * @return The ID of the customer.
     */
    
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the customer.
     *
     * @param id The ID to set for the customer.
     */
    
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the customer.
     *
     * @return The name of the customer.
     */
    
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer.
     *
     * @param name The name to set for the customer.
     */
    
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the phone number of the customer.
     *
     * @return The phone number of the customer.
     */
    
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the customer.
     *
     * @param phone The phone number to set for the customer.
     */
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Sets the list of bookings for the customer.
     *
     * @param bookings The list of bookings to set for the customer.
     */
    
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    /**
     * Removes the specified booking from the customer's list of bookings.
     *
     * @param booking The booking to be removed.
     */
    
    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }

    /**
     * Returns the email address of the customer.
     *
     * @return The email address of the customer.
     */
    
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the customer.
     *
     * @param email The email address to set for the customer.
     */
    
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Generates a short string representation of the customer's details.
     * The short representation includes the customer's ID, name, and phone number.
     *
     * @return A short string representation of the customer's details.
     */
    
    public String getDetailsShort() {
        return "Customer #" + id + "-" + name + " - " + phone;
    }

    /**
     * Generates a detailed string representation of the customer's details.
     * The detailed representation includes the customer's ID, name, phone number, and email address.
     *
     * @return A detailed string representation of the customer's details.
     */
    
    public String getDetailsLong() {
        StringBuilder details = new StringBuilder("Customer #" + id + "\n");
        details.append("Name: ").append(name).append("\n");
        details.append("Phone: ").append(phone).append("\n");
        details.append("Email:").append(email).append("\n");

        return details.toString();
    }

    /**
     * Adds a booking for the customer.
     * 
     * @param booking The booking to be added.
     * @throws FlightBookingSystemException If the customer already has a booking for the same flight.
     */
    
    public void addBooking(Booking booking) throws FlightBookingSystemException {
        if (hasBookingForFlight(booking.getFlight())) {
            throw new FlightBookingSystemException("Customer already has a booking for the same flight.");
        }
        bookings.add(booking);
    }

    /**
     * Checks if the customer has a booking for a specific flight.
     * 
     * @param flight The flight to check.
     * @return True if the customer has a booking for the flight, otherwise false.
     */
    
    public boolean hasBookingForFlight(Flight flight) {
        return findBookingForFlight(flight) != null;
    }

    /**
     * Finds the booking made by the customer for a specific flight.
     * 
     * @param flight The flight to find the booking for.
     * @return The booking made by the customer for the flight, or null if not found.
     */
    
    public Booking findBookingForFlight(Flight flight) {
        for (Booking booking : bookings) {
            if (booking.getFlight().equals(flight)) {
                return booking;
            }
        }
        return null;
    }

    /**
     * Cancels the booking made by the customer for the specified flight.
     * 
     * @param flight The flight for which the booking needs to be cancelled.
     * @throws FlightBookingSystemException If the customer does not have a booking for the specified flight.
     */
    
    public void cancelBookingForFlight(Flight flight) throws FlightBookingSystemException {
        // Find the booking for the specified flight
        Booking bookingToRemove = findBookingForFlight(flight);

        // If no booking is found for the specified flight, throw an exception
        if (bookingToRemove == null) {
            throw new FlightBookingSystemException("Customer does not have a booking for the specified flight.");
        }

        // Remove the booking from the list of bookings
        bookings.remove(bookingToRemove);
    }

    /**
     * Removes the booking made by the customer for the flight with the specified ID.
     * 
     * @param flightId The ID of the flight for which the booking needs to be removed.
     */
    
    public void removeBookingByFlightId(int flightId) {
        // Initialize a variable to store the booking to remove
        Booking bookingToRemove = null;
        
        // Iterate through the list of bookings
        for (Booking booking : bookings) {
            // Check if the ID of the flight associated with the booking matches the specified flight ID
            if (booking.getFlight().getId() == flightId) {
                // If a match is found, assign the booking to the variable and exit the loop
                bookingToRemove = booking;
                break;
            }
        }
        
        // If a booking is found, remove it from the list of bookings
        if (bookingToRemove != null) {
            bookings.remove(bookingToRemove);
        }
    }

    /**
     * Issues a booking for the customer on the specified flight with the given booking date and price.
     * 
     * @param flight The flight for which the booking is being issued.
     * @param bookingDate The date when the booking is made.
     * @param price The price of the booking.
     * @throws FlightBookingSystemException If adding the booking fails due to any reason, such as if the customer already has a booking for the same flight.
     */
    
    public void issueBooking(Flight flight, LocalDate bookingDate, double price) throws FlightBookingSystemException {
        // Create a new booking object for the customer on the specified flight with the provided booking date
        Booking booking = new Booking(this, flight, bookingDate);
        
        // Set the price of the booking
        booking.setPrice(price);
        
        // Add the booking to the flight's list of bookings for this customer
        flight.addBookingForCustomer(booking);
        
        // Add the booking to the customer's list of bookings
        addBooking(booking);
    }
}