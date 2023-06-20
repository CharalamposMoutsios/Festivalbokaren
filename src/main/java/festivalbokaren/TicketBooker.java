package festivalbokaren;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicketBooker {
    private List<Ticket> bookings;
    private int vipTicketCount = 1044;
    private int standardTicketCount = 3449;
    private int standardPlusTicketCount = 1884;
    private int officialTicketCount = 100;

    public TicketBooker() {
        this.bookings = new ArrayList<>();
    }

    public static void main(String[] args) {
        TicketBooker ticketBooker = new TicketBooker();
        ticketBooker.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    bookTicket();
                    break;
                case 2:
                    printSummary();
                    break;
                case 3:
                    loadBookings();
                    break;
                case 4:
                    saveBookings();
                    break;
                case 5:
                    isRunning = false;
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("----- Festival Booker -----");
        System.out.println("1. Book a ticket");
        System.out.println("2. Print summary of booked tickets");
        System.out.println("3. Load bookings");
        System.out.println("4. Save bookings");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private void bookTicket() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter year of birth: ");
        int yearOfBirth = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.println("Ticket types:");
        System.out.println("1. VIP");
        System.out.println("2. STANDARD");
        System.out.println("3. STANDARD_PLUS");
        System.out.println("4. OFFICIAL");
        System.out.print("Enter ticket type: ");
        int ticketTypeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        TicketType ticketType;
        switch (ticketTypeChoice) {
            case 1:
                ticketType = TicketType.VIP;
                if (vipTicketCount > 0) {
                    vipTicketCount--;
                    System.out.println("VIP ticket booked successfully!");
                } else {
                    System.out.println("No more VIP tickets available.");
                    return;
                }
                break;
            case 2:
                ticketType = TicketType.STANDARD;
                if (standardTicketCount > 0) {
                    standardTicketCount--;
                    System.out.println("Standard ticket booked successfully!");
                } else {
                    System.out.println("No more Standard tickets available.");
                    return;
                }
                break;
            case 3:
                ticketType = TicketType.STANDARD_PLUS;
                if (standardPlusTicketCount > 0) {
                    standardPlusTicketCount--;
                    System.out.println("Standard Plus ticket booked successfully!");
                } else {
                    System.out.println("No more Standard Plus tickets available.");
                    return;
                }
                break;
            case 4:
                ticketType = TicketType.OFFICIAL;
                if (officialTicketCount > 0) {
                    officialTicketCount--;
                    System.out.println("Official ticket booked successfully!");
                } else {
                    System.out.println("No more Official tickets available.");
                    return;
                }
                break;
            default:
                System.out.println("Invalid ticket type. Booking failed.");
                return;
        }

        // Create a new Ticket object with the entered details
        Ticket ticket = new Ticket(firstName, lastName, yearOfBirth, ticketType);

        // TODO: Add code to process the booked ticket (e.g., store in a list, database, etc.)
        bookings.add(ticket);

        System.out.println("Ticket booked successfully!");
    }

    private void printSummary() {
        System.out.println("Loaded bookings:");
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            for (Ticket ticket : bookings) {
                System.out.println(ticket);
            }
        }

        int vipTickets = 0;
        int standardTickets = 0;
        int standardPlusTickets = 0;
        int officialTickets = 0;

        for (Ticket ticket : bookings) {
            TicketType ticketType = ticket.getTicketType();
            switch (ticketType) {
                case VIP:
                    vipTickets++;
                    break;
                case STANDARD:
                    standardTickets++;
                    break;
                case STANDARD_PLUS:
                    standardPlusTickets++;
                    break;
                case OFFICIAL:
                    officialTickets++;
                    break;
            }
        }

        System.out.println("Summary of Booked Tickets:");
        System.out.println("VIP: " + vipTickets + " pcs");
        System.out.println("STANDARD: " + standardTickets + " pcs");
        System.out.println("STANDARD_PLUS: " + standardPlusTickets + " pcs");
        System.out.println("OFFICIAL: " + officialTickets + " pcs");
    }

    private void loadBookings() {
        try {
            Path filePath = Path.of("Festivalbokaren/bookings.json");
            String json = Files.readString(filePath);
            Ticket[] loadedBookings = new ObjectMapper().readValue(json, Ticket[].class);
            bookings.clear();
            bookings.addAll(Arrays.asList(loadedBookings));
            System.out.println("Bookings loaded successfully!");
        } catch (IOException e) {
            System.out.println("Failed to load bookings: " + e.getMessage());
        }
    }

    private void saveBookings() {
        try {
            Path filePath = Path.of("Festivalbokaren/bookings.json");
            String json = new ObjectMapper().writeValueAsString(bookings);
            Files.createDirectories(filePath.getParent());
            Files.writeString(filePath, json, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Bookings saved successfully!");
        } catch (IOException e) {
            System.out.println("Failed to save bookings: " + e.getMessage());
        }
    }
}
