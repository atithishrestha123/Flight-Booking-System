package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.EditBooking;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The UpdateBookingWindow class represents a GUI window for updating a booking.
 * It allows the user to input the booking ID and the new flight ID and update the booking accordingly.
 */
public class UpdateBookingWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField bookingIdText = new JTextField();
    private JTextField flightIdText = new JTextField();
    private JButton updateBtn = new JButton("Update");
    private JButton closeBtn = new JButton("Close");

    /**
     * Initializes an UpdateBookingWindow instance.
     * @param mainWindow The MainWindow instance to which the window belongs.
     */
    public UpdateBookingWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initializes the components of the window.
     */
    private void initialize() {
        setTitle("Update Booking");
        setSize(300, 150);
        setResizable(false);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 2));

        JLabel bookingIdLabel = new JLabel("Booking ID:");
        bookingIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(bookingIdLabel);

        bookingIdText.setHorizontalAlignment(JTextField.CENTER);
        topPanel.add(bookingIdText);

        JLabel flightIdLabel = new JLabel("Flight ID:");
        flightIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(flightIdLabel);

        flightIdText.setHorizontalAlignment(JTextField.CENTER);
        topPanel.add(flightIdText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 1));
        bottomPanel.add(updateBtn);
        bottomPanel.add(closeBtn);

        updateBtn.addActionListener(this);
        closeBtn.addActionListener(this);

        this.setLayout(new GridLayout(2, 1));
        this.add(topPanel);
        this.add(bottomPanel);

        setLocationRelativeTo(mw);
        setVisible(true);
    }

    /**
     * Handles the actionPerformed event when buttons are clicked.
     * @param ae The ActionEvent object representing the event.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateBtn) {
            updateBooking();
        } else if (ae.getSource() == closeBtn) {
            this.setVisible(false);
        }
    }

    /**
     * Updates the booking based on the entered booking ID and new flight ID.
     */
    private void updateBooking() {
        try {
            int bookingId = Integer.parseInt(bookingIdText.getText());
            int newFlightId = Integer.parseInt(flightIdText.getText());

            Command updateBookingCommand = new EditBooking(bookingId, newFlightId);
            updateBookingCommand.execute(mw.getFlightBookingSystem());

            mw.displayBookings();

            this.setVisible(false);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid booking and flight IDs",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
