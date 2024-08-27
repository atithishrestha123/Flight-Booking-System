package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.EditBooking;
import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class MainWindow extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu adminMenu;
    private JMenu flightsMenu;
    private JMenu bookingsMenu;
    private JMenu customersMenu;
    private JMenuItem adminShowcustomer;
    private JMenuItem adminShowbooking;
    private JMenuItem adminShowFlight;
    private JMenuItem adminExit;

    private JMenuItem flightsView;
    private JMenuItem flightsAdd;
    private JMenuItem flightsDel;

    private JMenuItem bookingView;
    private JMenuItem bookingsIssue;
    private JMenuItem bookingsUpdate;
    private JMenuItem bookingsCancel;

    private JMenuItem custView;
    private JMenuItem custAdd;
    private JMenuItem custDel;

    private FlightBookingSystem fbs;

    public MainWindow(FlightBookingSystem fbs) {

        initialize();
        this.fbs = fbs;

    }

    public FlightBookingSystem getFlightBookingSystem() {
        return fbs;
    }

    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Flight Booking Management System");

        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("./claudio.jpg"));

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setBackground(new Color(255, 255, 255, 200));

        setContentPane(backgroundPanel);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        adminMenu = new JMenu("Admin");
        adminShowbooking = new JMenuItem("Show Booking");
        adminShowcustomer = new JMenuItem("Show Customer");
        adminShowFlight = new JMenuItem("Show Flight");

        menuBar.add(adminMenu);
        adminMenu.add(adminShowbooking);
        adminMenu.add(adminShowcustomer);
        adminMenu.add(adminShowFlight);

        adminExit = new JMenuItem("Exit");
        adminMenu.add(adminExit);
        adminExit.addActionListener(this);
        for (int i = 0; i < adminMenu.getItemCount(); i++) {
            adminMenu.getItem(i).addActionListener(this);
        }

        flightsMenu = new JMenu("Flights");
        menuBar.add(flightsMenu);

        flightsView = new JMenuItem("View");
        flightsAdd = new JMenuItem("Add");
        flightsDel = new JMenuItem("Delete");
        flightsMenu.add(flightsView);
        flightsMenu.add(flightsAdd);
        flightsMenu.add(flightsDel);

        for (int i = 0; i < flightsMenu.getItemCount(); i++) {
            flightsMenu.getItem(i).addActionListener(this);
        }

        bookingsMenu = new JMenu("Bookings");
        menuBar.add(bookingsMenu);
        bookingView = new JMenuItem("View");
        bookingsIssue = new JMenuItem("Issue");
        bookingsUpdate = new JMenuItem("Update");
        bookingsCancel = new JMenuItem("Cancel");
        bookingsMenu.add(bookingView);
        bookingsMenu.add(bookingsIssue);
        bookingsMenu.add(bookingsUpdate);
        bookingsMenu.add(bookingsCancel);
        bookingsCancel.addActionListener(this);

        for (int i = 0; i < bookingsMenu.getItemCount(); i++) {
            bookingsMenu.getItem(i).addActionListener(this);
        }

        customersMenu = new JMenu("Customers");
        menuBar.add(customersMenu);

        custView = new JMenuItem("View");
        custAdd = new JMenuItem("Add");
        custDel = new JMenuItem("Delete");

        customersMenu.add(custView);
        customersMenu.add(custAdd);
        customersMenu.add(custDel);

        custView.addActionListener(this);
        custAdd.addActionListener(this);
        custDel.addActionListener(this);

        setSize(1000, 500);

        setVisible(true);
        setAutoRequestFocus(true);
        toFront();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        /*
         * Uncomment the following line to not terminate the console app when the window
         * is closed
         */
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    /* Uncomment the following code to run the GUI version directly from the IDE */
    public static void main(String[] args) throws IOException,
            FlightBookingSystemException {
        FlightBookingSystem fbs = FlightBookingSystemData.load();
        new MainWindow(fbs);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == adminExit) {

            try {
                FlightBookingSystemData.store(fbs);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
            }

            System.exit(0);
        } else if (ae.getSource() == adminShowbooking) {

            new ShowBookingWindow(this);

        } else if (ae.getSource() == adminShowcustomer) {
            new ShowCustomerWindow(this);

        } else if (ae.getSource() == adminShowFlight) {
            new ShowFlightWindow(this);

        } else if (ae.getSource() == flightsView) {
            displayFlights();

        } else if (ae.getSource() == flightsAdd) {
            new AddFlightWindow(this);

        } else if (ae.getSource() == flightsDel) {
            try {
                new DeleteFlightWindow(this);
            } catch (FlightBookingSystemException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == bookingView) {
            System.out.println("bookingView selected");
            displayBookings();

        } else if (ae.getSource() == bookingsIssue) {

            new AddBookingWindow(this);

        } else if (ae.getSource() == bookingsUpdate) {
            new UpdateBookingWindow(this);

        } else if (ae.getSource() == bookingsCancel) {
            new CancelBookingWindow(this);

        } else if (ae.getSource() == custView) {
            displayCustomers();

        } else if (ae.getSource() == custAdd) {
            new AddCustomerWindow(this);

        } else if (ae.getSource() == custDel) {
            try {
                new DeleteCustomerWindow(this);
            } catch (FlightBookingSystemException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    public void displayFlights() {
        List<Flight> flightsList = fbs.getFlights();

        String[] columns = { "Flight No", "Origin", "Destination", "Departure Date" };
        Object[][] data = new Object[flightsList.size()][columns.length];

        for (int i = 0; i < flightsList.size(); i++) {
            Flight flight = flightsList.get(i);
            data[i] = new Object[] { flight.getFlightNumber(), flight.getOrigin(),
                    flight.getDestination(), flight.getDepartureDate() };
        }

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        JTextField searchField = new JTextField();
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }

            private void search(String query) {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, 0));
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);

        JFrame flightsFrame = new JFrame("Flight Details");
        flightsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(new JLabel("Search by Flight No: "), BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        mainPanel.add(searchPanel, BorderLayout.NORTH);

        flightsFrame.getContentPane().add(mainPanel);
        flightsFrame.pack();
        flightsFrame.setLocationRelativeTo(null);
        flightsFrame.setVisible(true);
    }

    public void displayCustomers() {
        List<Customer> customersList = fbs.getCustomers();

        String[] columns = { "Customer ID", "Name", "Email", "Phone" };
        Object[][] data = new Object[customersList.size()][columns.length];

        for (int i = 0; i < customersList.size(); i++) {
            Customer customer = customersList.get(i);
            data[i] = new Object[] { customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone() };
        }

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        JTextField searchField = new JTextField();
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }

            private void search(String query) {
                sorter.setRowFilter(query.length() == 0 ? null : RowFilter.regexFilter("(?i)" + query));
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(200, 200, 200));
        tableHeader.setFont(tableHeader.getFont().deriveFont(Font.BOLD));

        int[] columnWidths = { 100, 200, 200, 150 };
        for (int i = 0; i < columns.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }

        JFrame customerFrame = new JFrame("Customer Details");
        customerFrame.setSize(800, 400);
        customerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customerFrame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(new JLabel("Search: "), BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        mainPanel.add(searchPanel, BorderLayout.NORTH);

        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        customerFrame.setLayout(new BorderLayout());
        customerFrame.add(mainPanel, BorderLayout.CENTER);

        customerFrame.setVisible(true);
    }

    public void displayBookings() {

        List<Booking> bookingsList = fbs.getBookings();
        String[] columns = { "Booking ID", "Customer Name", "Flight Number", "Departure Date" };
        Object[][] data = new Object[bookingsList.size()][columns.length];

        for (int i = 0; i < bookingsList.size(); i++) {
            Booking booking = bookingsList.get(i);
            data[i] = new Object[] { booking.getId(), booking.getCustomer().getName(),
                    booking.getFlight().getFlightNumber(), booking.getFlight().getDepartureDate() };
        }

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        JTextField searchField = new JTextField();
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }

            private void search(String query) {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, 0));

            }
        });

        JScrollPane scrollPane = new JScrollPane(table);

        JFrame bookingsFrame = new JFrame("Booking Details");
        bookingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(new JLabel("Search by Booking ID: "), BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        mainPanel.add(searchPanel, BorderLayout.NORTH);

        bookingsFrame.getContentPane().add(mainPanel);
        bookingsFrame.pack();
        bookingsFrame.setLocationRelativeTo(null);
        bookingsFrame.setVisible(true);
    }

}
