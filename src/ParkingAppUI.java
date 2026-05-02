import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ParkingAppUI extends JFrame {
    private final ParkingService parkingService = new ParkingService();
    private final BookingService bookingService = new BookingService();

    private JTable table;
    private DefaultTableModel tableModel;

    public ParkingAppUI() {
        setTitle("Parking Spot Booking System");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize table
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Buttons
        JButton viewSpotsBtn = new JButton("View Available Spots");
        JButton bookSpotBtn = new JButton("Book a Spot");
        JButton viewBookingsBtn = new JButton("View Bookings");
        JButton exitBtn = new JButton("Exit");

        // Button actions
        viewSpotsBtn.addActionListener(this::handleViewSpots);
        bookSpotBtn.addActionListener(this::handleBookSpot);
        viewBookingsBtn.addActionListener(this::handleViewBookings);
        exitBtn.addActionListener(e -> System.exit(0));

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.add(viewSpotsBtn);
        buttonPanel.add(bookSpotBtn);
        buttonPanel.add(viewBookingsBtn);
        buttonPanel.add(exitBtn);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleViewSpots(ActionEvent e) {
        String[] columns = {"Spot ID", "Location", "Total Slots", "Available Slots"};
        tableModel.setColumnIdentifiers(columns);
        tableModel.setRowCount(0); // Clear table

        List<ParkingSpot> spots = parkingService.getAvailableSpots();
        for (ParkingSpot spot : spots) {
            Object[] row = {
                spot.getId(),
                spot.getLocation(),
                spot.getTotalSlots(),
                spot.getAvailableSlots()
            };
            tableModel.addRow(row);
        }
    }

    private void handleBookSpot(ActionEvent e) {
        String spotIdStr = JOptionPane.showInputDialog(this, "Enter Parking Spot ID to Book:");
        String userName = JOptionPane.showInputDialog(this, "Enter Your Name:");

        if (spotIdStr != null && userName != null && !spotIdStr.trim().isEmpty() && !userName.trim().isEmpty()) {
            try {
                int spotId = Integer.parseInt(spotIdStr.trim());
                Booking booking = bookingService.bookParkingSpot(spotId, userName.trim(), parkingService);

                if (booking != null) {
                    JOptionPane.showMessageDialog(this, "Booking Successful!\n" + booking.toString());
                    handleViewSpots(null); // Refresh spots
                } else {
                    JOptionPane.showMessageDialog(this, "Booking Failed. Spot may be full or invalid.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Spot ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleViewBookings(ActionEvent e) {
        String[] columns = {"Booking ID", "User Name", "Spot ID", "Location"};
        tableModel.setColumnIdentifiers(columns);
        tableModel.setRowCount(0); // Clear table

        List<Booking> bookings = bookingService.getBookings();
        for (Booking booking : bookings) {
            ParkingSpot spot = booking.getParkingSpot();
            Object[] row = {
                booking.getBookingId(),
                booking.getUserName(),
                spot.getId(),
                spot.getLocation()
            };
            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ParkingAppUI app = new ParkingAppUI();
            app.setVisible(true);
        });
    }
}
        