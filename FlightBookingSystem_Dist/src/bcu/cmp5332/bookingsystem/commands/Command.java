package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.gui.UpdateBookingWindow;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The Command interface represents a generic command in the Flight Booking
 * System.
 * It defines a method for executing the command.
 * 
 * <p>
 * The commands in the Flight Booking System include operations such as listing
 * flights and customers,
 * adding new flights and customers, showing flight and customer details, adding
 * and canceling bookings,
 * updating bookings, loading the graphical user interface (GUI), displaying
 * help information, and exiting the program.
 * </p>
 */

public interface Command {

        /**
         * The predefined help message containing information about available commands
         * and their usage.
         */
        public static final String HELP_MESSAGE = "Commands:\n"
                        + "\tlistflights                               print all flights\n"
                        + "\tlistcustomers                             print all customers\n"
                        + "\taddflight                                 add a new flight\n"
                        + "\taddcustomer                               add a new customer\n"
                        + "\tshowflight [flight id]                    show flight details\n"
                        + "\tshowcustomer [customer id]                show customer details\n"
                        + "\taddbooking [customer id] [flight id]      add a new booking\n"
                        + "\tcancelbooking [customer id] [flight id]   cancel a booking\n"
                        + "\teditbooking [booking id] [flight id]      update a booking\n"
                        + "\tShowbooking [customer id]                 show booking details\n"
                        + "\tloadgui                                   loads the GUI version of the app\n"
                        + "\thelp                                      prints this help message\n"
                        + "\texit                                      exits the program";

        /**
         * Executes the command with the provided FlightBookingSystem instance.
         * 
         * @param flightBookingSystem The FlightBookingSystem instance on which the
         *                            command operates.
         * @throws FlightBookingSystemException if an error occurs while executing the
         *                                      command.
         */

        public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException;
}
