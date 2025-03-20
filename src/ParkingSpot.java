public class ParkingSpot {
    private int id;
    private String location;
    private int totalSlots;
    private int availableSlots;

    public ParkingSpot(int id, String location, int totalSlots, int availableSlots) {
        this.id = id;
        this.location = location;
        this.totalSlots = totalSlots;
        this.availableSlots = availableSlots;
    }

    public int getId() { return id; }
    public String getLocation() { return location; }
    public int getTotalSlots() { return totalSlots; }
    public int getAvailableSlots() { return availableSlots; }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }

    @Override
    public String toString() {
        return "ParkingSpot{id=" + id + ", location='" + location + "', totalSlots=" + totalSlots + ", availableSlots=" + availableSlots + "}";
    }
}
