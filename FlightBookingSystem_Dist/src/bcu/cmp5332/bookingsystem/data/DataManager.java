package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.IOException;

/**
 * The DataManager interface defines methods for loading and storing data of the Flight Booking System.
 * Classes implementing this interface are responsible for reading data from files and writing data to files.
 */

public interface DataManager {
    
	/**
     * The separator used to separate fields in the data files.
     */
    public static final String SEPARATOR = "::";
    
    /**
     * Loads data of the flight booking system from a data source into the provided FlightBookingSystem instance.
     * 
     * @param fbs The FlightBookingSystem instance into which the data is loaded.
     * @throws IOException if an I/O error occurs while accessing the data source.
     * @throws FlightBookingSystemException if an error occurs while processing the data.
     */
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException;
    
    /**
     * Stores data of the flight booking system from the provided FlightBookingSystem instance into a data source.
     * 
     * @param fbs the FlightBookingSystem instance from which the data is stored
     * @throws IOException if an I/O error occurs while accessing or writing to the data source
     */
    public void storeData(FlightBookingSystem fbs) throws IOException;
    
}
