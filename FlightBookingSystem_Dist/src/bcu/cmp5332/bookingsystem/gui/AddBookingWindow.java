package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.main.Main;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The AddBookingWindow class represents a graphical user interface window for adding a booking
 * in the Flight Booking System.
 * It extends the JFrame class and implements the ActionListener interface to handle user actions.
 */
public class AddBookingWindow extends JFrame implements ActionListener {

    private JTextField customerIdField;
    private JTextField flightIdField;
    private JButton addBtn;
    private JButton closeBtn;
    private FlightBookingSystem fbs;

    /**
     * Constructs an AddBookingWindow object.
     *
     * @param mainWindow The main window of the Flight Booking System GUI.
     */
    public AddBookingWindow(MainWindow mainWindow) {

        this.fbs = mainWindow.getFlightBookingSystem();

        setTitle("Add Booking");
        setSize(300, 150); // Adjusted size
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create components
        JLabel customerIdLabel = new JLabel("Customer ID:");
        customerIdField = new JTextField();
        JLabel flightIdLabel = new JLabel("Flight ID:");
        flightIdField = new JTextField();
        addBtn = new JButton("Add Booking");
        closeBtn = new JButton("Close");

        // Set layout
        setLayout(new BorderLayout());

        // Panel for input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(customerIdLabel);
        inputPanel.add(customerIdField);
        inputPanel.add(flightIdLabel);
        inputPanel.add(flightIdField);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));
        buttonPanel.add(addBtn);
        buttonPanel.add(closeBtn);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners to the buttons
        addBtn.addActionListener(this);
        closeBtn.addActionListener(this);

        // Set the window location relative to the main window
        setLocationRelativeTo(mainWindow);

        // Make the window visible
        setVisible(true);
    }

    /**
     * Handles the action events triggered by the buttons.
     *
     * @param e The ActionEvent object representing the user action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            // Get the customer and flight IDs from the text fields
            int customerId;
            int flightId;

            try {
                customerId = Integer.parseInt(customerIdField.getText());
                flightId = Integer.parseInt(flightIdField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid customer and flight IDs",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create an AddBooking command and execute it
            Command addBookingCommand = new AddBooking(customerId, flightId);
            try {
                addBookingCommand.execute(fbs);
                JOptionPane.showMessageDialog(this, "Booking added successfully", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (FlightBookingSystemException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Close the window
            dispose();
        } else if (e.getSource() == closeBtn) {
            // Handle close button action (you can add specific behavior here)
            dispose();
        }
    }
}
