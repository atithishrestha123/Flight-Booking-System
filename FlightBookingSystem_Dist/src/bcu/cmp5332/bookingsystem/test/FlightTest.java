package bcu.cmp5332.bookingsystem.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;

/**
 * FlightTest is a JUnit test class used to test the functionalities of the Flight class.
 */
public class FlightTest {

    private Flight flight;
    private Customer customer;

    /**
     * Initializes a Flight instance and a Customer instance for testing.
     */
    @Before
    public void setUp() {
        // Create a Flight instance for testing.
        flight = new Flight(1, "FL123", "Origin", "Destination", LocalDate.now(), 100, 200.0);
        
        // Create a Customer instance for testing.
        customer = new Customer(1, "John Doe", "john@example.com", "1234567890");
    }

    /**
     * Tests the getters and setters of the Flight class.
     * * It verifies that the getters return the expected values and that the setters correctly update the fields.
     */
    @Test
    public void testGettersAndSetters() {
    	 // Assert initial values.
        assertEquals(1, flight.getId());
        assertEquals("FL123", flight.getFlightNumber());
        assertEquals("Origin", flight.getOrigin());
        assertEquals("Destination", flight.getDestination());
        assertEquals(LocalDate.now(), flight.getDepartureDate());
        assertEquals(100, flight.getCapacity());
        assertEquals(200.0, flight.getPrice(), 0.001);

        // Test setters.
        flight.setId(2);
        flight.setFlightNumber("FL456");
        flight.setOrigin("New Origin");
        flight.setDestination("New Destination");
        LocalDate newDate = LocalDate.now().plusDays(1);
        flight.setDepartureDate(newDate);
        flight.setCapacity(150);
        flight.setPrice(250.0);

        // Assert updated values.
        assertEquals(2, flight.getId());
        assertEquals("FL456", flight.getFlightNumber());
        assertEquals("New Origin", flight.getOrigin());
        assertEquals("New Destination", flight.getDestination());
        assertEquals(newDate, flight.getDepartureDate());
        assertEquals(150, flight.getCapacity());
        assertEquals(250.0, flight.getPrice(), 0.001);
    }

    /**
     * Tests the addition of a passenger to the flight.
     * It adds a customer as a passenger to the flight and verifies that the passenger list contains the customer.
     */
    @Test
    public void testAddPassenger() {
    	// Add a passenger to the flight
        flight.addPassenger(customer);
        
        // Verify that the passenger list contains the added customer
        assertTrue(flight.getPassengers().contains(customer));
    }

    /**
     * Tests the removal of a passenger from the flight.
     * It adds a customer as a passenger to the flight, removes the passenger, and verifies that the passenger list does not contain the customer.
     */
    @Test
    public void testRemovePassenger() {
    	 // Add a passenger to the flight
        flight.addPassenger(customer);
        
        // Verify that the passenger list initially contains the added customer
        assertTrue(flight.getPassengers().contains(customer));
        
        // Remove the passenger from the flight
        flight.removePassenger(customer);
        
        // Verify that the passenger list no longer contains the removed customer
        assertFalse(flight.getPassengers().contains(customer));
    }
}
