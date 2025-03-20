public class Booking {
    private String bookingId;
    private String userName;
    private ParkingSpot parkingSpot;

    public Booking(String bookingId, String userName, ParkingSpot parkingSpot) {
        this.bookingId = bookingId;
        this.userName = userName;
        this.parkingSpot = parkingSpot;
    }

    public String getBookingId() { return bookingId; }
    public String getUserName() { return userName; }
    public ParkingSpot getParkingSpot() { return parkingSpot; }

    @Override
    public String toString() {
        return "Booking{id=" + bookingId + ", user='" + userName + "', spot=" + parkingSpot.getId() + ", location='" + parkingSpot.getLocation() + "'}";
    }
}
