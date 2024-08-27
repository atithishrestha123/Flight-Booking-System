package bcu.cmp5332.bookingsystem.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

/**
 * The DeleteCustomerWindow class represents a graphical user interface window for deleting a customer
 * from the Flight Booking System.
 * It extends the JFrame class.
 */
public class DeleteCustomerWindow extends JFrame {

    private MainWindow mainWindow;
    private JTextField customerIdField;

    /**
     * Constructs a DeleteCustomerWindow object.
     *
     * @param mainWindow The main window of the Flight Booking System GUI.
     * @throws FlightBookingSystemException If an error occurs during initialization.
     */
    public DeleteCustomerWindow(MainWindow mainWindow) throws FlightBookingSystemException {
        this.mainWindow = mainWindow;
        initialize();
    }

    /**
     * Initializes the components and layout of the DeleteCustomerWindow.
     *
     * @throws FlightBookingSystemException If an error occurs during initialization.
     */
    private void initialize() throws FlightBookingSystemException {
        setTitle("Delete Customer");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(mainWindow);

        customerIdField = new JTextField(10);
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCustomer();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.add(new JLabel("Enter Customer ID:"));
        mainPanel.add(customerIdField);
        mainPanel.add(deleteButton);

        add(mainPanel);
        setVisible(true);
    }

    /**
     * Deletes the customer based on the provided customer ID.
     */
    private void deleteCustomer() {
        try {
            int selectedCustomerId = Integer.parseInt(customerIdField.getText());
            boolean deleted = mainWindow.getFlightBookingSystem().deleteCustomer(selectedCustomerId);

            if (deleted) {
                JOptionPane.showMessageDialog(this, "Customer deleted successfully", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Customer not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid customer ID", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
