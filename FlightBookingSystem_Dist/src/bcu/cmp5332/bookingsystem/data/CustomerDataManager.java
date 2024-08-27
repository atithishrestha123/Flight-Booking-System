package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The CustomerDataManager class is responsible for loading customer data from a file into the FlightBookingSystem
 * and storing customer data from the FlightBookingSystem into a file.
 * It implements the DataManager interface and provides methods for loading and storing customer data.
 */
public class CustomerDataManager implements DataManager {

	// Private file path for the customer data.
    private final String RESOURCE = "./resources/data/customers.txt";

    
    /**
     * Loads customer data from the specified file into the FlightBookingSystem.
     * Each line in the file represents a customer with its properties separated by "::".
     * 
     * @param fbs The FlightBookingSystem instance into which the customer data is loaded.
     * @throws IOException if an I/O error occurs while reading the file.
     * @throws FlightBookingSystemException if an error occurs while parsing or processing the customer data.
     */
    
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        try (BufferedReader reader = new BufferedReader(new FileReader(RESOURCE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("::");

                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String phone = parts[2];
                    String email = parts[3];
                    Customer customer = new Customer(id, name, phone, email);
                    fbs.addCustomer(customer);
                } else {
                    System.out.println("Invalid data format in line: " + line);
                }
            }
        }
    }
    
    /**
     * Stores customer data from the FlightBookingSystem into the specified file.
     * Each customer's properties are written to the file, separated by "::".
     * 
     * @param fbs the FlightBookingSystem instance from which the customer data is stored
     * @throws IOException if an I/O error occurs while writing to the file
     */
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESOURCE))) {
            for (Customer customer : fbs.getCustomers()) {
                writer.write(customer.getId() + "::" + customer.getName() + "::" + customer.getPhone() + "::"
                        + customer.getEmail() + "::");
                writer.newLine();
            }

        }
    }
}
