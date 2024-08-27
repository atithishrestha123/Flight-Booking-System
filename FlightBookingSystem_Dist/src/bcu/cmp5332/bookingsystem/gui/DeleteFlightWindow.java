package bcu.cmp5332.bookingsystem.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

/**
 * The DeleteFlightWindow class represents a graphical user interface window for deleting a flight
 * from the Flight Booking System.
 * It extends the JFrame class and implements the ActionListener interface.
 */
public class DeleteFlightWindow extends JFrame implements ActionListener {

    private MainWindow mainWindow;
    private JTextField flightIdField;

    /**
     * Constructs a DeleteFlightWindow object.
     *
     * @param mainWindow The main window of the Flight Booking System GUI.
     * @throws FlightBookingSystemException If an error occurs during initialization.
     */
    public DeleteFlightWindow(MainWindow mainWindow) throws FlightBookingSystemException {
        this.mainWindow = mainWindow;
        initialize();
    }

    /**
     * Initializes the components and layout of the DeleteFlightWindow.
     *
     * @throws FlightBookingSystemException If an error occurs during initialization.
     */
    private void initialize() throws FlightBookingSystemException {
        setTitle("Delete Flight");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(mainWindow);

        JLabel flightIdLabel = new JLabel("Flight ID:");
        flightIdField = new JTextField(10);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            String enteredFlightId = flightIdField.getText();
            try {
                // Delete the flight from the system
                mainWindow.getFlightBookingSystem().deleteFlight(enteredFlightId);
                // Show a success message
                JOptionPane.showMessageDialog(this, "Flight deleted successfully", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                // Close the window after successful deletion
                dispose();
            } catch (FlightBookingSystemException ex) {
                // Show an error message if deletion fails
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        JPanel mainPanel = new JPanel();
        mainPanel.add(flightIdLabel);
        mainPanel.add(flightIdField);
        mainPanel.add(deleteButton);

        add(mainPanel);
        setVisible(true);
    }

    /**
     * Performs an action when an action event occurs.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Implement ActionListener methods if needed
    }
}
