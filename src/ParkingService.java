import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParkingService {
    public List<ParkingSpot> getAvailableSpots() {
        List<ParkingSpot> availableSpots = new ArrayList<>();
        String query = "SELECT * FROM ParkingSpots WHERE available_slots > 0";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                availableSpots.add(new ParkingSpot(
                    rs.getInt("id"), rs.getString("location"),
                    rs.getInt("total_slots"), rs.getInt("available_slots")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableSpots;
    }

    public ParkingSpot bookSpot(int spotId) {
        String selectQuery = "SELECT * FROM ParkingSpots WHERE id = ? AND available_slots > 0";
        String updateQuery = "UPDATE ParkingSpots SET available_slots = available_slots - 1 WHERE id = ? AND available_slots > 0";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {

            selectStmt.setInt(1, spotId);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                int availableSlots = rs.getInt("available_slots");
                if (availableSlots > 0) {
                    updateStmt.setInt(1, spotId);
                    updateStmt.executeUpdate();
                    return new ParkingSpot(rs.getInt("id"), rs.getString("location"), rs.getInt("total_slots"), availableSlots - 1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
