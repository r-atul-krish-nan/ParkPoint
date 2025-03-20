import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingService {
    public Booking bookParkingSpot(int spotId, String userName, ParkingService parkingService) {
        ParkingSpot spot = parkingService.bookSpot(spotId);
        if (spot == null) {
            return null;
        }

        String bookingId = UUID.randomUUID().toString();
        String query = "INSERT INTO Bookings (id, user_name, parking_spot_id) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, bookingId);
            stmt.setString(2, userName);
            stmt.setInt(3, spotId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return new Booking(bookingId, userName, spot);
    }

    public List<Booking> getBookings() {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT b.id, b.user_name, p.id AS spot_id, p.location, p.total_slots, p.available_slots FROM Bookings b " +
                       "JOIN ParkingSpots p ON b.parking_spot_id = p.id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ParkingSpot spot = new ParkingSpot(
                    rs.getInt("spot_id"), rs.getString("location"),
                    rs.getInt("total_slots"), rs.getInt("available_slots")
                );
                bookings.add(new Booking(rs.getString("id"), rs.getString("user_name"), spot));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
