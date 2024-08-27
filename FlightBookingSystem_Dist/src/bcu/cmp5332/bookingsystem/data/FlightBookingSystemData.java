package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The FlightBookingSystemData class is responsible for loading and storing the data of the Flight Booking System.
 * It manages a collection of DataManager objects for different data entities and provides methods to load and store
 * the data using these DataManager objects.
 */
public class FlightBookingSystemData {
    
	// List of data managers for different data entities.
    private static final List<DataManager> dataManagers = new ArrayList<>();
    
    // Static block to initialize data managers.
    static {
    	// Add data managers for flights, customers, and bookings.
        dataManagers.add(new FlightDataManager());
        
        /* Uncomment the two lines below when the implementation of their 
        loadData() and storeData() methods is complete */
         dataManagers.add(new CustomerDataManager());
         dataManagers.add(new BookingDataManager());
    }
    
    /**
     * Loads the data of the Flight Booking System from the data files into a FlightBookingSystem object.
     * 
     * @return the FlightBookingSystem instance with loaded data.
     * @throws FlightBookingSystemException if an error occurs while loading the data.
     * @throws IOException if an I/O error occurs while accessing the data files.
     */
    
    public static FlightBookingSystem load() throws FlightBookingSystemException, IOException {

        FlightBookingSystem fbs = new FlightBookingSystem();
        
        // Iterate over data managers and load data into the FlightBookingSystem.
        for (DataManager dm : dataManagers) {
            dm.loadData(fbs);
        }
        return fbs;
    }
    
    /**
     * Stores the data of the flight booking system from a FlightBookingSystem object into data files.
     * 
     * @param fbs The FlightBookingSystem instance from which the data is stored.
     * @throws IOException if an I/O error occurs while accessing or writing to the data files.
     */

    public static void store(FlightBookingSystem fbs) throws IOException {
    	// Iterate over data managers and store data from the FlightBookingSystem.
        for (DataManager dm : dataManagers) {
            dm.storeData(fbs);
        }
    }
    
}
