# ParkPoint

ParkPoint is a simple Java parking spot booking system backed by MySQL. It includes:

- A console application for viewing spots, booking a spot, and listing bookings
- A Swing desktop UI for the same core workflow
- A small SQL setup script with sample parking locations

## Features

- View parking spots that still have available capacity
- Book a parking spot for a user
- Store bookings in MySQL
- Review existing bookings from the console or desktop UI

## Tech Stack

- Java
- Swing
- MySQL
- MySQL Connector/J

## Project Structure

- `src/` - Java source files and the SQL setup script
- `lib/` - external libraries, including the MySQL JDBC driver
- `bin/` - compiled class files
- `.vscode/` - VS Code Java project settings

## Main Files

- `src/ParkingApp.java` - console entry point
- `src/ParkingAppUI.java` - Swing desktop entry point
- `src/ParkingService.java` - parking spot queries and booking updates
- `src/BookingService.java` - booking creation and listing
- `src/DatabaseConnection.java` - MySQL connection settings
- `src/ParkingDB` - database creation and seed data

## Prerequisites

Before running the project, make sure you have:

- Java installed
- MySQL Server running locally
- The database schema created from the provided SQL script

## Database Setup

1. Open MySQL.
2. Run the SQL script in `src/ParkingDB`.
3. Update the database credentials in `src/DatabaseConnection.java` if your local MySQL username or password is different.

The project currently expects:

- Database: `ParkingDB`
- Host: `localhost`
- Port: `3306`

## Configure Database Connection

Edit `src/DatabaseConnection.java` and verify these values match your environment:

```java
private static final String URL = "jdbc:mysql://localhost:3306/ParkingDB";
private static final String USER = "root";
private static final String PASSWORD = "root123*";
```

## Compile

From the project root:

```powershell
javac -cp "lib/mysql-connector-j-9.2.0.jar" -d bin src\*.java
```

## Run

Run the console version:

```powershell
java -cp "bin;lib/mysql-connector-j-9.2.0.jar" ParkingApp
```

Run the Swing desktop UI:

```powershell
java -cp "bin;lib/mysql-connector-j-9.2.0.jar" ParkingAppUI
```

## Console Menu

The console app provides these options:

1. View available parking spots
2. Book a parking spot
3. View bookings
4. Exit

## Sample Data

The SQL script seeds these parking locations:

- Mall
- Cinema
- Street

Each location starts with 50 total slots and 50 available slots.

## Notes

- `src/App.java` is still the default Hello World file and is not the main application entry point.
- Database credentials are hardcoded right now, so this project is best treated as a local learning or demo project unless configuration is externalized.
