package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.CancelBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The CancelBookingWindow class represents a graphical user interface window for canceling a booking
 * in the Flight Booking System.
 * It extends the JFrame class and implements the ActionListener interface to handle user actions.
 */
public class CancelBookingWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField customerIdText = new JTextField();
    private JTextField flightIdText = new JTextField();
    private JButton cancelBtn = new JButton("Cancel");
    private JButton closeBtn = new JButton("Close");

    /**
     * Constructs a CancelBookingWindow object.
     *
     * @param mw The main window of the Flight Booking System GUI.
     */
    public CancelBookingWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initializes the components and layout of the CancelBookingWindow.
     */
    private void initialize() {
        setTitle("Cancel Booking");
        setSize(300, 150);
        setResizable(false);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 2));

        JLabel customerIdLabel = new JLabel("Customer ID:");
        customerIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(customerIdLabel);

        customerIdText.setHorizontalAlignment(JTextField.CENTER);
        topPanel.add(customerIdText);

        JLabel flightIdLabel = new JLabel("Flight ID:");
        flightIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(flightIdLabel);

        flightIdText.setHorizontalAlignment(JTextField.CENTER);
        topPanel.add(flightIdText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));
        bottomPanel.add(cancelBtn);
        bottomPanel.add(closeBtn);

        cancelBtn.addActionListener(this);
        closeBtn.addActionListener(this);

        this.setLayout(new GridLayout(2, 1));
        this.add(topPanel);
        this.add(bottomPanel);

        setLocationRelativeTo(mw);
        setVisible(true);
    }

    /**
     * Handles the action events triggered by the buttons.
     *
     * @param ae The ActionEvent object representing the user action.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == cancelBtn) {
            cancelBooking();
        } else if (ae.getSource() == closeBtn) {
            this.setVisible(false);
        }
    }

    /**
     * Cancels a booking in the Flight Booking System.
     * Executes the CancelBooking command and displays appropriate messages.
     */
    private void cancelBooking() {
        try {
            int customerId = Integer.parseInt(customerIdText.getText());
            int flightId = Integer.parseInt(flightIdText.getText());

            Command cancelBookingCommand = new CancelBooking(customerId, flightId);
            cancelBookingCommand.execute(mw.getFlightBookingSystem());

            mw.displayBookings();

            this.setVisible(false);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid customer and flight IDs",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
