public class ParkingSpot {
    private int id;
    private String location;
    private boolean isAvailable;

    public ParkingSpot(int id, String location) {
        this.id = id;
        this.location = location;
        this.isAvailable = true; // Initially, all spots are available
    }

    public int getId() { return id; }
    public String getLocation() { return location; }
    public boolean isAvailable() { return isAvailable; }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    @Override
    public String toString() {
        return "ParkingSpot{id=" + id + ", location='" + location + "', available=" + isAvailable + "}";
    }
}
