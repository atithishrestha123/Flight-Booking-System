package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;

/**
 * The ListCustomers class represents a command to list all customers in the Flight Booking System.
 * It implements the Command interface and contains a method to execute the command.
 * 
 * <p>
 * When executed, this command retrieves a list of all customers from the FlightBookingSystem and
 * prints a summary of each customer, including their details in short format. It then prints the
 * total number of customers listed.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>{@code
 * ListCustomers listCustomersCommand = new ListCustomers();
 * listCustomersCommand.execute(flightBookingSystem);
 * }</pre>
 * </p>
 */

public class ListCustomers implements Command {
	
	/**
     * Executes the command to list all customers in the Flight Booking System.
     * 
     * @param flightBookingSystem The FlightBookingSystem instance from which customers are listed.
     * @throws FlightBookingSystemException if an error occurs while listing customers.
     */
	
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        
    	// Retrieve a list of all customers from the Flight Booking System.
    	List<Customer> customers = flightBookingSystem.getCustomers();
    	
    	 // Print a summary of each customer's details in short format.
        for (Customer customer : customers) {
            System.out.println(customer.getDetailsShort());
        }
        
     // Print the total number of customers listed.
        System.out.println(customers.size() + " customer(s)");
    }
}
