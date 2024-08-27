package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddCustomer;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The AddCustomerWindow class represents a graphical user interface window for adding a new customer
 * to the Flight Booking System.
 * It extends the JFrame class and implements the ActionListener interface to handle user actions.
 */
public class AddCustomerWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField nameText = new JTextField();
    private JTextField phoneText = new JTextField();
    private JTextField emailText = new JTextField();

    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * Constructs an AddCustomerWindow object.
     *
     * @param mw The main window of the Flight Booking System GUI.
     */
    public AddCustomerWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initializes the components and layout of the AddCustomerWindow.
     */
    private void initialize() {
        setTitle("Add a New Customer");
        setSize(350, 150);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 1));
        topPanel.add(new JLabel("Name : "));
        topPanel.add(nameText);
        topPanel.add(new JLabel("Phone : "));
        topPanel.add(phoneText);
        topPanel.add(new JLabel("Email : "));
        topPanel.add(emailText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(addBtn);
        bottomPanel.add(cancelBtn);

        addBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
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
        if (ae.getSource() == addBtn) {
            addCustomer();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }
    }

    /**
     * Adds a new customer to the Flight Booking System.
     * Executes the AddCustomer command and displays appropriate messages.
     */
    private void addCustomer() {
        try {
            String name = nameText.getText();
            String phone = phoneText.getText();
            String email = emailText.getText();

            Command addCustomer = new AddCustomer(name, phone, email);
            addCustomer.execute(mw.getFlightBookingSystem());

            JOptionPane.showMessageDialog(this, "Customer " + name + " has been added successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            this.setVisible(false);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
