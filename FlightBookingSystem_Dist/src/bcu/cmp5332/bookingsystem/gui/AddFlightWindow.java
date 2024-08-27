package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddFlight;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The AddFlightWindow class represents a graphical user interface window for adding a new flight
 * to the Flight Booking System.
 * It extends the JFrame class and implements the ActionListener interface to handle user actions.
 */
public class AddFlightWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField flightNoText = new JTextField();
    private JTextField originText = new JTextField();
    private JTextField destinationText = new JTextField();
    private JTextField depDateText = new JTextField();
    private JTextField capacityText = new JTextField();
    private JTextField priceText = new JTextField();
    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * Constructs an AddFlightWindow object.
     *
     * @param mw The main window of the Flight Booking System GUI.
     */
    public AddFlightWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initializes the components and layout of the AddFlightWindow.
     */
    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            // Handle the exception (optional)
        }

        setTitle("Add a New Flight");
        setSize(350, 220);

        // Create panels
        JPanel topPanel = createTopPanel();
        JPanel bottomPanel = createBottomPanel();

        // Set layout
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Add action listeners
        addBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        setLocationRelativeTo(mw);
        setVisible(true);
    }

    /**
     * Creates and configures the top panel containing input fields for flight details.
     *
     * @return The configured top panel.
     */
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(6, 2));

        topPanel.add(new JLabel("Flight No : "));
        topPanel.add(flightNoText);
        topPanel.add(new JLabel("Origin : "));
        topPanel.add(originText);
        topPanel.add(new JLabel("Destination : "));
        topPanel.add(destinationText);
        topPanel.add(new JLabel("Departure Date (YYYY-MM-DD) : "));
        topPanel.add(depDateText);
        topPanel.add(new JLabel("Capacity : "));
        topPanel.add(capacityText);
        topPanel.add(new JLabel("Price : "));
        topPanel.add(priceText);

        return topPanel;
    }

    /**
     * Creates and configures the bottom panel containing action buttons.
     *
     * @return The configured bottom panel.
     */
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        bottomPanel.add(addBtn);
        bottomPanel.add(cancelBtn);

        return bottomPanel;
    }

    /**
     * Handles the action events triggered by the buttons.
     *
     * @param ae The ActionEvent object representing the user action.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addBtn) {
            addFlight();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }
    }

    /**
     * Adds a new flight to the Flight Booking System.
     * Executes the AddFlight command and displays appropriate messages.
     */
    private void addFlight() {
        try {
            // Parse input fields
            String flightNumber = flightNoText.getText();
            String origin = originText.getText();
            String destination = destinationText.getText();

            LocalDate departureDate = LocalDate.parse(depDateText.getText());

            int capacity = Integer.parseInt(capacityText.getText());
            double price = Double.parseDouble(priceText.getText());

            // Create and execute the AddFlight Command
            Command addFlight = new AddFlight(flightNumber, origin, destination, departureDate, capacity, price);
            addFlight.execute(mw.getFlightBookingSystem());

            // Show a notification indicating the added flight
            JOptionPane.showMessageDialog(this, "Flight " + flightNumber + " has been added successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            // Hide (close) the AddFlightWindow
            this.setVisible(false);
        } catch (DateTimeParseException | NumberFormatException | FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
