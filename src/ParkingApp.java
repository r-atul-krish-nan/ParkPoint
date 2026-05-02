import java.util.List;
import java.util.Scanner;

public class ParkingApp {
    public static void main(String[] args) {
        ParkingService parkingService = new ParkingService();
        BookingService bookingService = new BookingService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Parking Spot Booking ===");
            System.out.println("1. View Available Parking Spots");
            System.out.println("2. Book a Parking Spot");
            System.out.println("3. View Bookings");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    List<ParkingSpot> availableSpots = parkingService.getAvailableSpots();
                    System.out.println(availableSpots.isEmpty() ? "No available spots." : availableSpots);
                    break;

                case 2:
                    System.out.print("Enter Parking Spot ID to Book: ");
                    int spotId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter your name: ");
                    String userName = scanner.nextLine();
                    Booking booking = bookingService.bookParkingSpot(spotId, userName, parkingService);
                    System.out.println(booking != null ? "Booking successful! " + booking : "Failed to book.");
                    break;

                case 3:
                    System.out.println(bookingService.getBookings());
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
            }
        }
    }
}
