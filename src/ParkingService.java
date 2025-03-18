import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParkingService {
    public List<ParkingSpot> getAvailableSpots() {
        List<ParkingSpot> availableSpots = new ArrayList<>();
        String query = "SELECT * FROM ParkingSpots WHERE is_available = TRUE";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                availableSpots.add(new ParkingSpot(rs.getInt("id"), rs.getString("location")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableSpots;
    }

    public ParkingSpot bookSpot(int spotId) {
        String query = "UPDATE ParkingSpots SET is_available = FALSE WHERE id = ? AND is_available = TRUE";
        String selectQuery = "SELECT * FROM ParkingSpots WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {

            stmt.setInt(1, spotId);
            int updated = stmt.executeUpdate();

            if (updated > 0) {
                selectStmt.setInt(1, spotId);
                ResultSet rs = selectStmt.executeQuery();
                if (rs.next()) {
                    return new ParkingSpot(rs.getInt("id"), rs.getString("location"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
