package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.ShowBooking;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ShowBookingWindow class represents a graphical user interface window for displaying booking details
 * for a specific customer in the Flight Booking System.
 * It extends the JFrame class and implements the ActionListener interface to handle user actions.
 */
public class ShowBookingWindow extends JFrame implements ActionListener {

    private JTextField customerIdField;
    private JButton showBookingButton;
    private JTextArea resultArea;
    private MainWindow mainWindow;

    /**
     * Constructs a ShowBookingWindow object.
     *
     * @param mainWindow The main window of the Flight Booking System GUI.
     */
    public ShowBookingWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        initialize();
    }

    /**
     * Initializes the components and layout of the ShowBookingWindow.
     */
    private void initialize() {
        setTitle("Show Booking");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel customerIdLabel = new JLabel("Customer ID:");
        customerIdField = new JTextField(10);
        showBookingButton = new JButton("Show Booking");
        inputPanel.add(customerIdLabel);
        inputPanel.add(customerIdField);
        inputPanel.add(showBookingButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        showBookingButton.addActionListener(this);

        setContentPane(mainPanel);
        setLocationRelativeTo(mainWindow);
        setVisible(true);
    }

    /**
     * Handles the action events triggered by the button.
     *
     * @param e The ActionEvent object representing the user action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showBookingButton) {
            showBooking();
        }
    }

    /**
     * Retrieves and displays booking details for the specified customer.
     * Executes the ShowBooking command and updates the result area with the booking information.
     */
    private void showBooking() {
        try {
            int customerId = Integer.parseInt(customerIdField.getText());
            ShowBooking showBooking = new ShowBooking(customerId);
            showBooking.execute(mainWindow.getFlightBookingSystem());
            resultArea.setText(showBooking.getResult());
        } catch (NumberFormatException | FlightBookingSystemException ex) {
            resultArea.setText("Invalid customer ID");
        }
    }
}
