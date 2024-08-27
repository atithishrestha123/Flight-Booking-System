package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * The BookingDataManager class is responsible for loading booking data from a file into the FlightBookingSystem
 * and storing booking data from the FlightBookingSystem into a file.
 * It implements the DataManager interface and provides methods for loading and storing booking data.
 */

public class BookingDataManager implements DataManager {

	// File path for the booking data.
    public final String RESOURCE = "./resources/data/bookings.txt";

    /**
     * Loads booking data from the specified file into the FlightBookingSystem.
     * Each line in the file represents a booking with customer ID, flight ID, and booking date separated by "::".
     * 
     * @param fbs The FlightBookingSystem instance into which the booking data is loaded.
     * @throws IOException if an I/O error occurs while reading the file.
     * @throws FlightBookingSystemException if an error occurs while parsing or processing the booking data.
     */
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        try (BufferedReader reader = new BufferedReader(new FileReader(RESOURCE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("::");
                int customerId = Integer.parseInt(data[0]);
                int flightId = Integer.parseInt(data[1]);
                LocalDate bookDate = LocalDate.parse(data[2]);
                Customer customer = fbs.getCustomerByID(customerId);
                Flight flight = fbs.getFlightByID(flightId);
                Booking booking = new Booking(customer, flight, bookDate);

                fbs.addBooking(booking);
                flight.addPassenger(customer);
                flight.addBookingForCustomer(booking);
            }
        }
    }
    
    /**
     * Stores booking data from the FlightBookingSystem into the specified file.
     * Each booking's customer ID, flight ID, and booking date are written to the file, separated by "::".
     * 
     * @param fbs The FlightBookingSystem instance from which the booking data is stored.
     * @throws IOException if an I/O error occurs while writing to the file.
     */
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESOURCE))) {
            for (Booking booking : fbs.getBookings()) {
                writer.write(booking.getCustomer().getId() + "::" + booking.getFlight().getId() + "::"
                        + booking.getBookingDate().toString() + "::");
                writer.newLine();
            }
        }
    }
}
