package bcu.cmp5332.bookingsystem.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

/**
 * This class contains unit tests for the Customer class.
 * It tests various functionalities of the Customer class such as getters and setters,
 * adding bookings, canceling bookings, removing bookings by flight ID, and issuing bookings.
 */

public class CustomerTest {

	// Fields for testing.
    private Customer customer;
    private Flight flight;
    private Booking booking;

    /**
     * Sets up the initial state before each test.
     */
    @Before
    public void setUp() {
        // Create a Customer instance for testing.
        customer = new Customer(1, "John Doe", "1234567890", "john@example.com");
        
        // Create a Flight instance for testing.
        flight = new Flight(1, "FL123", "Origin", "Destination", LocalDate.now(), 100, 200.0);
        
        // Create a Booking instance for testing.
        booking = new Booking(customer, flight, LocalDate.now());
    }

    /**
     * Tests the getters and setters of the Customer class.
     */
    @Test
    public void testGettersAndSetters() {
    	// Assert initial state.
        assertEquals(1, customer.getId());
        assertEquals("John Doe", customer.getName());
        assertEquals("1234567890", customer.getPhone());
        assertEquals("john@example.com", customer.getEmail());

        // Test setters.
        customer.setId(2);
        customer.setName("Jane Doe");
        customer.setPhone("9876543210");
        customer.setEmail("jane@example.com");

        // Assert after setting new values.
        assertEquals(2, customer.getId());
        assertEquals("Jane Doe", customer.getName());
        assertEquals("9876543210", customer.getPhone());
        assertEquals("jane@example.com", customer.getEmail());
    }

    /**
     * Tests the addition of a booking to the customer's bookings list.
     * @throws FlightBookingSystemException if an error occurs during the test.
     */
    @Test
    public void testAddBooking() throws FlightBookingSystemException {
        customer.addBooking(booking);
        assertTrue(customer.hasBookingForFlight(flight));
        assertEquals(booking, customer.findBookingForFlight(flight));
    }

    /**
     * Tests adding a duplicate booking, which should throw an exception.
     * @throws FlightBookingSystemException if an error occurs during the test.
     */
    @Test(expected = FlightBookingSystemException.class)
    public void testAddDuplicateBooking() throws FlightBookingSystemException {
        customer.addBooking(booking);
        customer.addBooking(booking); // Adding the same booking again should throw an exception
    }

    /**
     * Tests canceling a booking for a flight.
     * @throws FlightBookingSystemException if an error occurs during the test.
     */
    @Test
    public void testCancelBooking() throws FlightBookingSystemException {
        customer.addBooking(booking);
        assertTrue(customer.hasBookingForFlight(flight));

        customer.cancelBookingForFlight(flight);
        assertFalse(customer.hasBookingForFlight(flight));
    }

    /**
     * Tests canceling a non-existing booking, which should throw an exception.
     * @throws FlightBookingSystemException if an error occurs during the test.
     */
    @Test(expected = FlightBookingSystemException.class)
    public void testCancelNonExistingBooking() throws FlightBookingSystemException {
        customer.cancelBookingForFlight(flight); 
    }

    /**
     * Tests removing a booking by flight ID.
     * @throws FlightBookingSystemException if an error occurs during the test.
     */
    @Test
    public void testRemoveBookingByFlightId() throws FlightBookingSystemException {
        customer.addBooking(booking);
        assertTrue(customer.hasBookingForFlight(flight));

        customer.removeBookingByFlightId(flight.getId());
        assertFalse(customer.hasBookingForFlight(flight));
    }

    /**
     * Tests issuing a booking for a flight.
     * @throws FlightBookingSystemException if an error occurs during the test.
     */
    @Test
    public void testIssueBooking() throws FlightBookingSystemException {
        customer.issueBooking(flight, LocalDate.now(), 200.0);
        assertTrue(customer.hasBookingForFlight(flight));
        assertEquals(200.0, customer.findBookingForFlight(flight).getPrice(), 0.001);
    }
}
