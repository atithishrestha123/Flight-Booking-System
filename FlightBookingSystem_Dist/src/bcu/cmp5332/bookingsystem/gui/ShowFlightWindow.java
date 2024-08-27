package bcu.cmp5332.bookingsystem.gui;

import javax.swing.*;

import bcu.cmp5332.bookingsystem.commands.ShowFlight;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ShowFlightWindow class represents a GUI window for displaying details of a specific flight.
 * It allows the user to input a flight ID and click a button to show the details of the corresponding flight.
 */
public class ShowFlightWindow extends JFrame implements ActionListener {

    private JTextField flightIdField;
    private JTextArea resultArea;
    private JButton showFlightButton; 
    private MainWindow mainWindow;

    /**
     * Initializes a ShowFlightWindow instance.
     * @param parentWindow The MainWindow instance to which the window belongs.
     */
    public ShowFlightWindow(MainWindow parentWindow) {
        setTitle("Show Flight Details");
        setSize(400, 300);
        setLocationRelativeTo(parentWindow);

        this.mainWindow = parentWindow;

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Flight ID input field
        JPanel flightIdPanel = new JPanel();
        JLabel flightIdLabel = new JLabel("Flight ID:");
        flightIdField = new JTextField(10);
        flightIdPanel.add(flightIdLabel);
        flightIdPanel.add(flightIdField);

        // Result area to display flight details
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Show Flight button
        showFlightButton = new JButton("Show Flight");
        showFlightButton.addActionListener(this);

        mainPanel.add(flightIdPanel);
        mainPanel.add(scrollPane);
        mainPanel.add(showFlightButton);

        add(mainPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Handles the actionPerformed event when the "Show Flight" button is clicked.
     * It invokes the showFlightDetails method.
     * @param e The ActionEvent object representing the event.
     */
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showFlightButton) {
            showFlightDetails();
        }
    }
/**
     * Retrieves the flight details based on the entered flight ID and displays them in the result area.
     */
    private void showFlightDetails() {
        try {
            // Get the flight ID from the input field
            int flightId = Integer.parseInt(flightIdField.getText());

            // Create a ShowFlight command and execute it
            ShowFlight showFlightCommand = new ShowFlight(flightId);
            showFlightCommand.execute(mainWindow.getFlightBookingSystem());

            // Display the result in the result area
            resultArea.setText(showFlightCommand.getResult());
        } catch (NumberFormatException ex) {
            resultArea.setText("Invalid Flight ID. Please enter a valid numeric ID.");
        } catch (FlightBookingSystemException ex) {
            resultArea.setText("Error: " + ex.getMessage());
        }
    }
}
