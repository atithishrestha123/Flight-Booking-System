package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.ShowCustomer;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
     * The ShowCustomerWindow class represents a GUI window for displaying details of a specific customer.
     * It allows the user to input a customer ID and click a button to show the details of the corresponding customer.
     */
public class ShowCustomerWindow extends JFrame implements ActionListener {

    private JTextField customerIdField;
    private JButton showCustomerButton;
    private JTextArea resultArea;
    private MainWindow mainWindow;

    
    /**
     * Initializes a ShowCustomerWindow instance.
     * @param mainWindow The MainWindow instance to which the window belongs.
     */
    public ShowCustomerWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        initialize();
    }
    
/**
     * Initializes the components of the window.
     */
    private void initialize() {
        setTitle("Show Customer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel customerIdLabel = new JLabel("Customer ID:");
        customerIdField = new JTextField(10);
        showCustomerButton = new JButton("Show Customer");
        inputPanel.add(customerIdLabel);
        inputPanel.add(customerIdField);
        inputPanel.add(showCustomerButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        showCustomerButton.addActionListener(this);

        setContentPane(mainPanel);
        setLocationRelativeTo(mainWindow);
        setVisible(true);
    }
    
    /**
     * Handles the actionPerformed event when the "Show Customer" button is clicked.
     * It invokes the showCustomer method.
     * @param e The ActionEvent object representing the event.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showCustomerButton) {
            showCustomer();
        }
    }

    /**
     * Retrieves the customer details based on the entered customer ID and displays them in the result area.
     */
    
    private void showCustomer() {
        try {
            int customerId = Integer.parseInt(customerIdField.getText());
            ShowCustomer showCustomer = new ShowCustomer(customerId);
            showCustomer.execute(mainWindow.getFlightBookingSystem());
            resultArea.setText(showCustomer.getResult());
        } catch (NumberFormatException | FlightBookingSystemException ex) {
            resultArea.setText("Invalid customer ID");
        }
    }
}
