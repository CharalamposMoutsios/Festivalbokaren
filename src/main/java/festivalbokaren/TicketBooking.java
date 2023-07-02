package festivalbokaren;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.InputMismatchException;

public class TicketBooking {
    private List<EventTicket> bookings;
    private Map<TicketType, Integer> ticketCounts;


    //Constructs a new instance of TicketBooking
    //Initializes the booking list and ticketCounts map.

    public TicketBooking() {
        this.bookings = new ArrayList<>();
        this.ticketCounts = new HashMap<>();
        initializeTicketCounts();
    }

    //Returns the list of bookings
    /*
    * @return the booking list
    */


    public List<EventTicket> getBookings() {
        return bookings;
    }

    public Map<TicketType, Integer> getTicketCounts() {
        return ticketCounts;
    }

    private void initializeTicketCounts() {
        ticketCounts.put(TicketType.VIP, 1044);
        ticketCounts.put(TicketType.STANDARD, 3449);
        ticketCounts.put(TicketType.STANDARD_PLUS, 1884);
        ticketCounts.put(TicketType.OFFICIAL, 100);
    }

    public static void main(String[] args) {
        TicketBooking ticketBooker = new TicketBooking();
        ticketBooker.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            displayMenu();

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        bookTicket(scanner);
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
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume invalid input
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

    void bookTicket(Scanner scanner) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter year of birth: ");
        int yearOfBirth;
        try {
            yearOfBirth = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid year of birth. Booking failed.");
            scanner.nextLine(); // Consume invalid input
            return;
        }
        scanner.nextLine(); // Consume newline character

        System.out.println("Ticket types:");
        System.out.println("1. VIP");
        System.out.println("2. STANDARD");
        System.out.println("3. STANDARD_PLUS");
        System.out.println("4. OFFICIAL");
        System.out.print("Enter ticket type: ");
        int ticketTypeChoice;
        try {
            ticketTypeChoice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid ticket type. Booking failed.");
            scanner.nextLine(); // Consume invalid input
            return;
        }
        scanner.nextLine(); // Consume newline character

        TicketType ticketType;

        switch (ticketTypeChoice) {
            case 1:
                ticketType = TicketType.VIP;
                break;
            case 2:
                ticketType = TicketType.STANDARD;
                break;
            case 3:
                ticketType = TicketType.STANDARD_PLUS;
                break;
            case 4:
                ticketType = TicketType.OFFICIAL;
                break;
            default:
                System.out.println("Invalid ticket type. Booking failed.");
                return;
        }

        int availableTickets = ticketCounts.get(ticketType);
        if (availableTickets > 0) {
            ticketCounts.put(ticketType, availableTickets - 1);
            EventTicket booking = new EventTicket(firstName, lastName, yearOfBirth, ticketType);
            bookings.add(booking);
            System.out.println("Ticket booked successfully!");
        } else {
            System.out.println("No more tickets of type " + ticketType + " available.");
        }
    }

    void printSummary() {
        System.out.println("Loaded bookings:");
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            for (EventTicket ticket : bookings) {
                System.out.println(ticket);
            }
        }

        System.out.println("Summary of Booked Tickets:");
        for (Map.Entry<TicketType, Integer> entry : ticketCounts.entrySet()) {
            TicketType ticketType = entry.getKey();
            int count = entry.getValue();
            System.out.println(ticketType + ": " + count + " pcs");
        }
    }

    void loadBookings() {
        try {
            Path filePath = Path.of("Festivalbokaren/bookings.json");
            String json = Files.readString(filePath);
    
            ObjectMapper objectMapper = new ObjectMapper();
            EventTicket[] loadedBookings = objectMapper.readValue(json, EventTicket[].class);
            
            bookings.clear();
            bookings.addAll(Arrays.asList(loadedBookings));
            
            System.out.println("Bookings loaded successfully!");
        } catch (IOException e) {
            System.out.println("Failed to load bookings: " + e.getMessage());
        }
    }
    

    void saveBookings() {
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

	public void loadBookings(Path invalidFilePath) {
	}
}
