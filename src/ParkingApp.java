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
                    if (availableSpots.isEmpty()) {
                        System.out.println("No available spots.");
                    } else {
                        System.out.println("Available Parking Spots:");
                        for (ParkingSpot spot : availableSpots) {
                            System.out.println(spot);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter Parking Spot ID to Book: ");
                    int spotId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter your name: ");
                    String userName = scanner.nextLine();

                    Booking booking = bookingService.bookParkingSpot(spotId, userName, parkingService);
                    if (booking != null) {
                        System.out.println("Booking successful! " + booking);
                    } else {
                        System.out.println("Failed to book. Spot may be unavailable.");
                    }
                    break;

                case 3:
                    List<Booking> bookings = bookingService.getBookings();
                    if (bookings.isEmpty()) {
                        System.out.println("No bookings found.");
                    } else {
                        System.out.println("Bookings:");
                        for (Booking b : bookings) {
                            System.out.println(b);
                        }
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
