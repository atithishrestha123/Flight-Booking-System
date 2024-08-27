package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The FlightBookingSystem class represents the core functionality of the Flight
 * Booking System.
 * It manages flights, customers, and bookings within the system.
 */
public class FlightBookingSystem {

    // System date for the Flight Booking System.
    private final LocalDate systemDate = LocalDate.parse("2020-11-11");

    // Collection of customers and flights stored in maps for easy retrieval.
    private final Map<Integer, Customer> customers = new TreeMap<>();
    private final Map<Integer, Flight> flights = new TreeMap<>();

    // List to store all bookings in the system.
    private final List<Booking> bookings = new ArrayList<>();

    // Static variable to keep track of the next available customer ID.
    private static int nextCustomerId = 1;

    /**
     * Constructs a FlightBookingSystem object.
     * Initializes the system date and data structures for customers, flights, and
     * bookings.
     */
    public FlightBookingSystem() {
        // The system date is set to a predefined value.
        // This can be adjusted if needed to reflect the current date.
        // For the purpose of this system, a fixed date is used for simplicity.
    }

    /**
     * Returns the system date of the Flight Booking System.
     *
     * @return the system date.
     */
    public LocalDate getSystemDate() {
        return systemDate;
    }

    /**
     * Returns a list of all flights in the system.
     *
     * @return the list of flights.
     */
    public List<Flight> getFlights() {
        return new ArrayList<>(flights.values());
    }

    /**
     * Retrieves a flight by its ID.
     *
     * @param id The ID of the flight to retrieve.
     * @return The flight with the specified ID.
     * @throws FlightBookingSystemException if no flight is found with the provided
     *                                      ID.
     */
    public Flight getFlightByID(int id) throws FlightBookingSystemException {
        if (!flights.containsKey(id)) {
            throw new FlightBookingSystemException("There is no flight with that ID.");
        }
        return flights.get(id);
    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param id The ID of the customer to retrieve.
     * @return The customer with the specified ID.
     * @throws FlightBookingSystemException if no customer is found with the
     *                                      provided ID.
     */
    public Customer getCustomerByID(int id) throws FlightBookingSystemException {
        if (!customers.containsKey(id)) {
            throw new FlightBookingSystemException("There is no customer with that ID.");
        }
        return customers.get(id);
    }

    /**
     * Retrieves a flight by its flight number.
     *
     * @param flightNumber The Flight Number to search for
     * @return The Flight with the specified flight number, or null if not found.
     */
    public Flight getFlightByNumber(String flightNumber) {
        for (Flight flight : flights.values()) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null; // If flight not found
    }

    /**
     * Adds a new flight to the system.
     *
     * @param flight The Flight to add.
     * @throws FlightBookingSystemException if a flight with the same ID already
     *                                      exists.
     */
    public void addFlight(Flight flight) throws FlightBookingSystemException {
        if (flights.containsKey(flight.getId())) {
            throw new FlightBookingSystemException("Flight with ID " + flight.getId() + " already exists.");
        }
        flights.put(flight.getId(), flight);
    }

    /**
     * Adds a new customer to the Flight Booking System.
     *
     * @param customer The Customer to add.
     * @throws FlightBookingSystemException if a customer with the same email
     *                                      already exists.
     */
    public void addCustomer(Customer customer) throws FlightBookingSystemException {
        // Check for existing customer with the same email.
        for (Customer existingCustomer : customers.values()) {
            if (existingCustomer.getEmail().equalsIgnoreCase(customer.getEmail())) {
                throw new FlightBookingSystemException(
                        "Customer with email " + customer.getEmail() + " already exists.");
            }
        }

        // Assign a new ID to the customer if not provided.
        if (customer.getId() == 0) {
            customer.setId(getNextCustomerId());
        } else {
            // Update nextCustomerId to ensure uniqueness of IDs.
            nextCustomerId = Math.max(nextCustomerId, customer.getId() + 1);
        }

        // Add the customer to the system.
        customers.put(customer.getId(), customer);
    }

    /**
     * Cancels a booking for a specified customer and flight.
     * 
     * @param customerId The ID of the customer whose booking is to be cancelled.
     * @param flightId   The ID of the flight for which the booking is to be
     *                   cancelled.
     * @throws FlightBookingSystemException if the customer does not have a booking
     *                                      for the specified flight.
     */
    public void cancelBooking(int customerId, int flightId) throws FlightBookingSystemException {
        Booking bookingToRemove = null;
        Flight flightToUpdate = null;

        // Find the booking and flight associated with the provided IDs.
        for (Booking booking : bookings) {
            if (booking.getCustomer().getId() == customerId && booking.getFlight().getId() == flightId) {
                bookingToRemove = booking;
                flightToUpdate = booking.getFlight();
                break;
            }
        }

        // If the booking and flight are found, proceed with cancellation.
        if (bookingToRemove != null && flightToUpdate != null) {
            // Update booking status to reflect cancellation.
            bookingToRemove.setCancelled(true);

            // Remove the passenger from the flight.
            flightToUpdate.removePassenger(bookingToRemove.getCustomer());

            // Remove the booking from the customer.
            bookingToRemove.getCustomer().removeBooking(bookingToRemove);

            // Remove the booking from the system.
            bookings.remove(bookingToRemove);

            System.out.println("Booking was cancelled successfully.");
        } else {
            throw new FlightBookingSystemException("Customer does not have a booking for the specified flight.");
        }
    }

    /**
     * Retrieves a booking by its unique identifier.
     * 
     * @param bookingId the unique identifier of the booking to retrieve.
     * @return the booking with the specified ID, or {@code null} if no booking is
     *         found.
     */
    public Booking getBookingById(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.getId() == bookingId) {
                return booking;
            }
        }
        return null;
    }

    /**
     * Edits a booking by changing the associated flight.
     * 
     * @param bookingId The Unique Identifier of the booking to edit.
     * @param flightId  The Unique Identifier of the new flight to associate with
     *                  the booking.
     * @throws FlightBookingSystemException if the booking, old flight, or new
     *                                      flight is not found,
     *                                      or if there is an issue editing the
     *                                      booking.
     */
    public void editBooking(int bookingId, int flightId) throws FlightBookingSystemException {
        // Retrieve the booking, old flight, and customer associated with the provided
        // IDs.
        Booking booking = getBookingById(bookingId);
        Flight oldFlight = booking.getFlight();
        Customer customer = booking.getCustomer();
        Flight newFlight = getFlightByID(flightId);

        // Check if the booking, old flight, and new flight are found.
        if (booking == null || oldFlight == null || newFlight == null) {
            throw new FlightBookingSystemException("Booking or Flight not found. Booking cannot be edited.");
        }

        // Remove the old booking.
        oldFlight.removePassenger(customer);
        oldFlight.removeBooking(booking);
        customer.removeBooking(booking);
        bookings.remove(booking);

        // Create a new booking with the updated flight and add it to the system.
        Booking newBooking = new Booking(customer, newFlight, LocalDate.now());
        addBooking(newBooking);
        newFlight.addPassenger(customer);
        newFlight.addBookingForCustomer(newBooking);
    }

    /**
     * Removes the specified booking from the list of bookings.
     * 
     * @param booking the booking to be removed.
     */
    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }

    /**
     * Retrieves the next available customer ID and increments the counter.
     * 
     * @return the next available customer ID.
     */
    public static int getNextCustomerId() {
        return nextCustomerId++;
    }

    /**
     * Adds the provided booking to the list of bookings.
     * 
     * @param booking the booking to be added.
     */
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    /**
     * Retrieves a list of all bookings in the system.
     * 
     * @return a list of all bookings.
     */
    public List<Booking> getBookings() {
        return bookings;
    }

    /**
     * Retrieves a list of all customers in the system.
     * 
     * @return a list of all customers.
     */
    public List<Customer> getCustomers() {
        return new ArrayList<>(customers.values());
    }

    /**
     * Deletes a customer with the specified ID from the Flight Booking System.
     * 
     * @param customerId The ID of the customer to be deleted.
     * @return true if the customer is successfully deleted, false otherwise.
     * @throws FlightBookingSystemException if the customer with the specified ID is not found.
     */
    public boolean deleteCustomer(int customerId) throws FlightBookingSystemException {
        Customer customerToDelete = customers.get(customerId);

        if (customerToDelete != null) {
            customers.remove(customerId);
            return true;
        } else {
            throw new FlightBookingSystemException("Customer not found with ID: " + customerId);
        }
    }

    /**
     * Deletes a flight with the specified flight number from the Flight Booking System.
     * 
     * @param selectedFlightNumber The flight number of the flight to be deleted.
     * @throws FlightBookingSystemException if the flight with the specified flight number is not found.
     */
    public void deleteFlight(String selectedFlightNumber) throws FlightBookingSystemException {
        Flight flightToDelete = null;
        for (Flight flight : flights.values()) {
            if (flight.getFlightNumber().equals(selectedFlightNumber)) {
                flightToDelete = flight;
                break;
            }
        }

        if (flightToDelete != null) {
            flights.remove(flightToDelete.getId());
        } else {
            throw new FlightBookingSystemException("Flight not found with Flight Number: " + selectedFlightNumber);
        }
    }

}
