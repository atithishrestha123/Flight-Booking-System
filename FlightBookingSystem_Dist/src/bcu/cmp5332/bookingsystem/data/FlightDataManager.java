package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.swing.JPopupMenu.Separator;

/**
 * The FlightDataManager class is responsible for loading flight data from a file into the FlightBookingSystem
 * and storing flight data from the FlightBookingSystem into a file.
 * It implements the DataManager interface and provides methods for loading and storing flight data.
 */

public class FlightDataManager implements DataManager {

	// File path for the flight data.
    private final String RESOURCE = "./resources/data/flights.txt";
    
    // Separator used to separate fields in the data file.
    private final String SEPARATOR = "::";

    /**
     * Loads flight data from the specified file into the FlightBookingSystem.
     * Each line in the file represents a flight with its properties separated by the specified separator.
     * 
     * @param fbs The FlightBookingSystem instance into which the flight data is loaded.
     * @throws IOException if an I/O error occurs while reading the file.
     * @throws FlightBookingSystemException if an error occurs while parsing or processing the flight data.
     */
    
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int lineIdx = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                lineIdx++;
                String[] properties = line.split(SEPARATOR, -1);
                if (properties.length >= 7 && !properties[0].isEmpty()) {
                    try {
                        int id = Integer.parseInt(properties[0]);
                        String flightNumber = properties[1];
                        String origin = properties[2];
                        String destination = properties[3];
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate departureDate = LocalDate.parse(properties[4], formatter);
                        int capacity = Integer.parseInt(properties[5]);
                        double price = Double.parseDouble(properties[6]);
                        
                        Flight flight = new Flight(id, flightNumber, origin, destination, departureDate, capacity,
                                price);
                        fbs.addFlight(flight);
                    } catch (NumberFormatException ex) {
                        throw new FlightBookingSystemException(
                                "Error parsing flight properties on line " + lineIdx + ": " + line);
                    }
                } else {
                    throw new FlightBookingSystemException("Invalid data on line " + lineIdx + ": " + line);
                }
            }
        }
    }
    
    /**
     * Stores flight data from the FlightBookingSystem into the specified file.
     * Each flight's properties are written to the file, separated by the specified separator.
     * 
     * @param fbs the FlightBookingSystem instance from which the flight data is stored.
     * @throws IOException if an I/O error occurs while writing to the file.
     */
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Flight flight : fbs.getFlights()) {
                out.print(flight.getId() + SEPARATOR);
                out.print(flight.getFlightNumber() + SEPARATOR);
                out.print(flight.getOrigin() + SEPARATOR);
                out.print(flight.getDestination() + SEPARATOR);
                out.print(flight.getDepartureDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + SEPARATOR);

                out.print(flight.getCapacity() + SEPARATOR);
                out.print(flight.getPrice() + SEPARATOR);
                out.println();
            }
        }
    }
}