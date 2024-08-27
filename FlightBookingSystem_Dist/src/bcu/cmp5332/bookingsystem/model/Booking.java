package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

/**
 * The Booking class represents a booking made by a customer for a flight in the Flight Booking System.
 * It contains information such as the booking date, customer details, flight details, and booking status.
 */
public class Booking {

	private LocalDate date; // The date of the flight.
    private double price; // The price of the booking.
    private Customer customer; // The customer who made the booking.
    private Flight flight; // The flight booked by the customer.
    private LocalDate bookingDate; // The date when the booking was made.
    private int id; // The unique identifier for the booking.
    private boolean cancelled; // Indicates whether the booking has been cancelled.

    private static int lastID = 0; // Static variable to track the last assigned booking ID.

    /**
     * Constructs a new Booking object with the specified customer, flight, and booking date.
     *
     * @param customer The customer who made the booking.
     * @param flight The flight booked by the customer.
     * @param bookingDate The date when the booking was made.
     */

    public Booking(Customer customer, Flight flight, LocalDate bookingDate) {
        this.id = ++lastID; // Increment the last assigned ID and assign it to the current booking
        this.customer = customer; // Set the customer of the booking
        this.flight = flight; // Set the flight of the booking
        this.bookingDate = bookingDate; // Set the booking date
    }
    
    /**
     * Returns the unique identifier for the booking.
     *
     * @return The ID of the booking.
     */
    
    public int getId() {
        return id;
    }
    
    /**
     * Returns the customer who made the booking.
     *
     * @return The customer of the booking.
     */

    public Customer getCustomer() {
        return customer;
    }
    
    /**
     * Sets the customer who made the booking.
     *
     * @param customer The customer to set for the booking.
     */

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    /**
     * Returns the flight booked by the customer.
     *
     * @return The flight of the booking.
     */

    public Flight getFlight() {
        return flight;
    }
    
    /**
     * Sets the flight booked by the customer.
     *
     * @param flight The flight to set for the booking.
     */

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    
    /**
     * Returns the date when the booking was made.
     *
     * @return The booking date.
     */

    public LocalDate getBookingDate() {
        return bookingDate;
    }
    
    /**
     * Sets the date when the booking was made.
     *
     * @param bookingDate The booking date to set.
     */

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    /**
     * Returns the date of the flight.
     *
     * @return The date of the flight.
     */

    public LocalDate getDate() {
        return date;
    }
    
    /**
     * Sets the date of the flight.
     *
     * @param date The date of the flight to set.
     */

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    /**
     * Returns the price of the booking.
     *
     * @return The price of the booking.
     */

    public double getPrice() {
        return price;
    }
    
    /**
     * Sets the price of the booking.
     *
     * @param price The price of the booking to set.
     */

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns true if the booking has been cancelled, otherwise false.
     *
     * @return True if the booking is cancelled, otherwise false.
     */
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets the cancellation status of the booking.
     *
     * @param cancelled True to mark the booking as cancelled, otherwise false.
     */
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    /**
     * Returns a string containing the details of the booking.
     *
     * @return The details of the booking.
     */

    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Booking Date: ").append(bookingDate).append("\n");
        details.append("Customer Name: ").append(customer.getName()).append("\n");
        details.append("Flight Details: ").append(flight.getDetailsShort()).append("\n");
        
        return details.toString();
    }

}